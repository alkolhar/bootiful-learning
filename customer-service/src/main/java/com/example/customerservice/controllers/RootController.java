package com.example.customerservice.controllers;

import com.example.customerservice.controllers.customer.CustomerController;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RootController {

    @GetMapping("/")
    ResponseEntity<RepresentationModel> root() {
        RepresentationModel model = new RepresentationModel();
        model.add(linkTo(methodOn(RootController.class).root()).withSelfRel());
        model.add(linkTo(methodOn(CustomerController.class).listAll()).withRel("users"));

        return ResponseEntity.ok(model);
    }
}
