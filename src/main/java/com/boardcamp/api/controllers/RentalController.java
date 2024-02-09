package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.DTOs.RentalDTO;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.services.RentalService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping()
    public ResponseEntity<List<RentalModel>> getRentals() {
        List<RentalModel> rentals = rentalService.findRentals();

        return ResponseEntity.status(HttpStatus.OK).body(rentals);
    }

    @PostMapping()
    public ResponseEntity<RentalModel> postRental(@RequestBody @Valid RentalDTO rentalDTO) {
        RentalModel rental = rentalService.createRental(rentalDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(rental);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<RentalModel> putFinalizeRental(@PathVariable("id") Long id) {
        RentalModel rental = rentalService.updateFinalizeRental(id);

        return ResponseEntity.status(HttpStatus.OK).body(rental);
    }
}
