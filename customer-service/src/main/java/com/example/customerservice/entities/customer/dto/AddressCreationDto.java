package com.example.customerservice.entities.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

@Value
public class AddressCreationDto implements Serializable {
    @NotNull
    @NotBlank
    String addressLine1;
    @NotNull
    @NotBlank
    String postalCode;
    @NotNull
    Long stateProvinceId;
}