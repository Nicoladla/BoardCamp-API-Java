package com.boardcamp.api.DTOs;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RentalDTO {

    @Positive
    private int daysRented;

    @Positive
    private Long gameId;

    @Positive
    private Long customerId;
}
