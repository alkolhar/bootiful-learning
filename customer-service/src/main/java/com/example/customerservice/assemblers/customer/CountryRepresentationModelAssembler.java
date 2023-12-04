package com.example.customerservice.assemblers.customer;

import com.example.common.config.model.SimpleIdentifiableRepresentationModelAssembler;
import com.example.customerservice.controllers.customer.CountryController;
import com.example.customerservice.entities.customer.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryRepresentationModelAssembler extends SimpleIdentifiableRepresentationModelAssembler<Country> {
    public CountryRepresentationModelAssembler() {
        super(CountryController.class);
    }
}
