package com.example.customerservice;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.customerservice.entities.customer.Customer}
 */
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