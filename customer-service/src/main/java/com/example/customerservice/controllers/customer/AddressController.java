package com.example.customerservice.controllers.customer;

import com.example.customerservice.assemblers.customer.AddressRepresentationModelAssembler;
import com.example.customerservice.services.customer.StateProvinceService;
import com.example.customerservice.entities.customer.Address;
import com.example.customerservice.entities.customer.StateProvince;
import com.example.customerservice.entities.customer.dto.AddressCreationDto;
import com.example.customerservice.services.customer.AddressService;
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
@RequestMapping("address")
public class AddressController {
    private final AddressService addressService;
    private final StateProvinceService stateProvinceService;
    private final AddressRepresentationModelAssembler assembler;

    public AddressController(AddressService addressService, StateProvinceService stateProvinceService, AddressRepresentationModelAssembler assembler) {
        this.addressService = addressService;
        this.stateProvinceService = stateProvinceService;
        this.assembler = assembler;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('managers')")
    public ResponseEntity<EntityModel<Address>> create(@Valid @RequestBody AddressCreationDto creationDto) {
        StateProvince state = stateProvinceService.get(creationDto.getStateProvinceId());

        Address address = new Address();
        address.setAddressLine1(creationDto.getAddressLine1());
        address.setPostalCode(creationDto.getPostalCode());
        address.setStateProvince(state);

        return ResponseEntity.ok(assembler.toModel(addressService.create(address)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<EntityModel<Address>> get(@NotNull @PathVariable Long id) {
        return ResponseEntity.ok(assembler.toModel(addressService.get(id)));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CollectionModel<EntityModel<Address>>> listAll() {
        return ResponseEntity.ok(
                assembler.toCollectionModel(addressService.listAll())
        );
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('managers')")
    public ResponseEntity<EntityModel<Address>> update(@PathVariable Long id, @Valid @RequestBody Address input) throws BadRequestException {
        if (!Objects.equals(id, input.getId())) {
            throw new BadRequestException("Id from url does not match input!");
        }
        return ResponseEntity.ok(assembler.toModel(addressService.update(input)));
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('managers')")
    public Address delete(@NotNull @PathVariable Long id) {
        return addressService.delete(id);
    }
}
