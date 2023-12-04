package com.example.customerservice.entities.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.io.Serializable;

@Value
public class CustomerCreationDto implements Serializable {
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @Email
    String email;
    Long addressId;
}