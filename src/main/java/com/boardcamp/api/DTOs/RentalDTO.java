package com.boardcamp.api.DTOs;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RentalDTO {

    @Positive(message = "daysRented must be greater than 0")
    private int daysRented;

    @Positive(message = "gameId must be greater than 0")
    private Long gameId;

    @Positive(message = "customerId must be greater than 0")
    private Long customerId;
}
