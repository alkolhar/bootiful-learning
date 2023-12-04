package com.example.customerservice.controllers.customer;

import com.example.customerservice.CustomerCreationDto;
import com.example.customerservice.assemblers.customer.CustomerRepresentationModelAssembler;
import com.example.customerservice.entities.customer.Address;
import com.example.customerservice.services.customer.AddressService;
import com.example.customerservice.services.customer.CustomerService;
import com.example.customerservice.entities.customer.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.BadRequestException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final AddressService addressService;
    private final CustomerRepresentationModelAssembler assembler;

    public CustomerController(CustomerService customerService, AddressService addressService, CustomerRepresentationModelAssembler assembler) {
        this.customerService = customerService;
        this.addressService = addressService;
        this.assembler = assembler;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('managers')")
    public ResponseEntity<EntityModel<Customer>> create(@Valid @RequestBody CustomerCreationDto creationDto) {
        Address address = addressService.get(creationDto.getAddressId());

        Customer customer = new Customer();
        customer.setFirstName(creationDto.getFirstName());
        customer.setLastName(creationDto.getLastName());
        customer.setEmail(creationDto.getEmail());
        customer.setAddress(address);

        return ResponseEntity.ok(assembler.toModel(customerService.create(customer)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<EntityModel<Customer>> get(@NotNull @PathVariable Long id) {
        return ResponseEntity.ok(assembler.toModel(customerService.get(id)));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CollectionModel<EntityModel<Customer>>> listAll() {
        return ResponseEntity.ok(
                assembler.toCollectionModel(customerService.listAll())
        );
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('managers')")
    public ResponseEntity<EntityModel<Customer>> update(@PathVariable Long id, @Valid @RequestBody Customer input) throws BadRequestException {
        if (!Objects.equals(id, input.getId())) {
            throw new BadRequestException("Id from url does not match input!");
        }
        return ResponseEntity.ok(assembler.toModel(customerService.update(input)));
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('managers')")
    public Customer delete(@NotNull @PathVariable Long id) {
        return customerService.delete(id);
    }
}
