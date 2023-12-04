package com.example.customerservice.entities.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

@Value
public class StateProvinceCreationDto implements Serializable {
    @NotNull
    @NotBlank
    String name;
    @NotNull
    @Size(min = 2, max = 2)
    @NotBlank
    String abbreviation;
    @NotNull
    Long countryId;
}