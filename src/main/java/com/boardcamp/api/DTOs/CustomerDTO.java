package com.boardcamp.api.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotBlank(message = "Name cannot be null or empty.")
    @Size(min = 3, message = "Name must have more than two characters")
    private String name;

    @NotBlank(message = "CPF cannot be null or empty.")
    @Pattern(regexp = "^[0-9]{11}$", message = "Invalid CPF format. It must have 11 numeric digits.")
    private String cpf;
}
