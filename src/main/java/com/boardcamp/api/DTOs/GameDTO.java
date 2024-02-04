package com.boardcamp.api.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GameDTO {

    @NotBlank(message = "name cannot be null or empty.")
    @Size(min = 2, message = "name cannot have less than two characters.")
    private String name;

    @NotBlank(message = "image cannot be null or empty.")
    private String image;

    @Positive(message = "stockTotal must be greater than 0")
    private int stockTotal;

    @Positive(message = "pricePerDay must be greater than 0")
    private int pricePerDay;
}
