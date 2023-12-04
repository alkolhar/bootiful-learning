package com.example.customerservice.entities.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address_line_1", nullable = false)
    private String addressLine1;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "state_province_id")
    private StateProvince stateProvince;

    public static Address newInstance(String addressLine1, String postalCode, StateProvince stateProvince) {
        Address address = new Address();
        address.setAddressLine1(addressLine1);
        address.setPostalCode(postalCode);
        address.setStateProvince(stateProvince);
        return address;
    }

}