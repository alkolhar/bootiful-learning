package com.example.customerservice.assemblers.customer;

import com.example.common.config.model.SimpleIdentifiableRepresentationModelAssembler;
import com.example.customerservice.controllers.customer.AddressController;
import com.example.customerservice.entities.customer.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressRepresentationModelAssembler extends SimpleIdentifiableRepresentationModelAssembler<Address> {
    public AddressRepresentationModelAssembler() {
        super(AddressController.class);
    }

}