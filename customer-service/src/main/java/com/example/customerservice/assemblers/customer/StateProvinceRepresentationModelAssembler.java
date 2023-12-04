package com.example.customerservice.assemblers.customer;

import com.example.common.config.model.SimpleIdentifiableRepresentationModelAssembler;
import com.example.customerservice.controllers.customer.StateProvinceController;
import com.example.customerservice.entities.customer.StateProvince;
import org.springframework.stereotype.Component;

@Component
public class StateProvinceRepresentationModelAssembler extends SimpleIdentifiableRepresentationModelAssembler<StateProvince> {
    public StateProvinceRepresentationModelAssembler() {
        super(StateProvinceController.class);
    }
}