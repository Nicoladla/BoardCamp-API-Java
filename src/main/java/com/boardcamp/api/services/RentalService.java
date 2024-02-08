package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.DTOs.RentalDTO;
import com.boardcamp.api.exceptions.NotFoundException;
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

        
    }

}
