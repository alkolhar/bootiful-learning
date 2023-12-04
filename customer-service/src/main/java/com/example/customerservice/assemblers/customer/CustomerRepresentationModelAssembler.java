package com.example.customerservice.assemblers.customer;

import com.example.common.config.model.SimpleIdentifiableRepresentationModelAssembler;
import com.example.customerservice.controllers.customer.AddressController;
import com.example.customerservice.controllers.customer.CustomerController;
import com.example.customerservice.entities.customer.Customer;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerRepresentationModelAssembler extends SimpleIdentifiableRepresentationModelAssembler<Customer> {
    public CustomerRepresentationModelAssembler() {
        super(CustomerController.class);
    }

    @Override
    public void addLinks(EntityModel<Customer> resource) {
        super.addLinks(resource);
        resource.add(linkTo(methodOn(AddressController.class).get(resource.getContent().getAddress().getId())).withRel("address"));
    }
}
