package com.boardcamp.api.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GameDTO {

    @NotBlank(message = "name cannot be null.")
    @Min(value = 2, message = "name cannot have less than two characters.")
    private String name;

    @NotBlank(message = "image cannot be null.")
    @Pattern(regexp = "((http|https)://)(www.)?[a-zA-Z0-9@:%._\\\\+~#?&//=]{2,256}\\\\.[a-z]{2,6}\\\\b([-a-zA-Z0-9@:%._\\\\+~#?&//=]*)")
    private String image;

    @NotBlank(message = "stockTotal cannot be null.")
    @Positive
    private int stockTotal;

    @NotBlank(message = "pricePerDay cannot be null.")
    @Positive
    private int pricePerDay;
}
