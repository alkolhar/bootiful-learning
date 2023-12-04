package com.example.customerservice.entities.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "state_province")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StateProvince {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Size(min = 2, max = 2)
    @NotNull
    @NotBlank
    @Column(name = "abbreviation", nullable = false, unique = true, length = 2)
    private String abbreviation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;


    public static StateProvince newInstance(String name, String abbreviation, Country country) {
        StateProvince stateProvince = new StateProvince();
        stateProvince.setName(name);
        stateProvince.setAbbreviation(abbreviation);
        stateProvince.setCountry(country);
        return stateProvince;
    }

}