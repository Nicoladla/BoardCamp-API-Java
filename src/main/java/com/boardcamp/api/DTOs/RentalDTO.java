package com.boardcamp.api.DTOs;

import jakarta.validation.constraints.Positive;

public class RentalDTO {

    @Positive
    private int daysRented;

    @Positive
    private int gameId;

    @Positive
    private int customerId;
}
