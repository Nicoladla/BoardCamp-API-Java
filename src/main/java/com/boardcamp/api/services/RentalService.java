package com.boardcamp.api.services;

import org.springframework.stereotype.Service;

import com.boardcamp.api.repositories.RentalRepository;

@Service
public class RentalService {
    private RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }
}
