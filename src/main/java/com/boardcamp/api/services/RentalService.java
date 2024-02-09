package com.boardcamp.api.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.DTOs.RentalDTO;
import com.boardcamp.api.exceptions.NotFoundException;
import com.boardcamp.api.exceptions.UnprocessableEntityException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.repositories.RentalRepository;

@Service
public class RentalService {
    private RentalRepository rentalRepository;
    private CustomerRepository customerRepository;
    private GameRepository gameRepository;

    public RentalService(RentalRepository rentalRepository, CustomerRepository customerRepository,
            GameRepository gameRepository) {
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
        this.gameRepository = gameRepository;
    }

    public List<RentalModel> findRentals() {
        List<RentalModel> rentals = rentalRepository.findAll();

        return rentals;
    }

    public RentalModel createRental(RentalDTO rentalDTO) {
        GameModel game = gameRepository.findById(rentalDTO.getGameId())
                .orElseThrow(() -> new NotFoundException("Game not found."));

        CustomerModel customer = customerRepository.findById(rentalDTO.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer not found."));

        Long rentedGames = rentalRepository.countRentalsPerGame(game.getId());
        boolean isTheGameAvailable = rentedGames < game.getStockTotal();

        if (!isTheGameAvailable)
            throw new UnprocessableEntityException("Game currently unavailable. Out of stock!");

        LocalDate rentDate = LocalDate.now();
        int originalRentalPrice = rentalDTO.getDaysRented() * game.getPricePerDay();

        RentalModel rental = new RentalModel(rentalDTO, rentDate, originalRentalPrice, game, customer);

        rentalRepository.save(rental);

        return rental;
    }

    public RentalModel updateFinalizeRental(Long id) {
        RentalModel rental = rentalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rental not found."));

        boolean isTheRentalFinished = rental.getReturnDate() != null;
        if (isTheRentalFinished)
            throw new UnprocessableEntityException("The rental has already been finalized.");

        LocalDate returnDate = LocalDate.now();
        int actualDaysRented = Period.between(rental.getRentDate(), returnDate).getDays();
        int daysDelay = actualDaysRented - rental.getDaysRented();
        int delayFee = daysDelay * rental.getGame().getPricePerDay();

        RentalModel newRental = new RentalModel(rental, returnDate, delayFee);

        rentalRepository.save(newRental);

        return newRental;
    }
}
