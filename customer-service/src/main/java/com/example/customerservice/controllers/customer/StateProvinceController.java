package com.example.customerservice.controllers.customer;

import com.example.customerservice.assemblers.customer.StateProvinceRepresentationModelAssembler;
import com.example.customerservice.entities.customer.Country;
import com.example.customerservice.entities.customer.StateProvince;
import com.example.customerservice.entities.customer.dto.StateProvinceCreationDto;
import com.example.customerservice.services.customer.CountryService;
import com.example.customerservice.services.customer.StateProvinceService;
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
@RequestMapping("state")
public class StateProvinceController {
    private final StateProvinceService stateProvinceService;
    private final CountryService countryService;
    private final StateProvinceRepresentationModelAssembler assembler;

    public StateProvinceController(StateProvinceService stateProvinceService, CountryService countryService, StateProvinceRepresentationModelAssembler assembler) {
        this.stateProvinceService = stateProvinceService;
        this.countryService = countryService;
        this.assembler = assembler;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('managers')")
    public ResponseEntity<EntityModel<StateProvince>> create(@Valid @RequestBody StateProvinceCreationDto creationDto) {
        Country country = countryService.get(creationDto.getCountryId());
        StateProvince state = StateProvince.newInstance(creationDto.getName(), creationDto.getAbbreviation(), country);

        return ResponseEntity.ok(assembler.toModel(stateProvinceService.create(state)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<EntityModel<StateProvince>> get(@NotNull @PathVariable Long id) {
        return ResponseEntity.ok(assembler.toModel(stateProvinceService.get(id)));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CollectionModel<EntityModel<StateProvince>>> listAll() {
        return ResponseEntity.ok(
                assembler.toCollectionModel(stateProvinceService.listAll())
        );
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('managers')")
    public ResponseEntity<EntityModel<StateProvince>> update(@PathVariable Long id, @Valid @RequestBody StateProvince input) throws BadRequestException {
        if (!Objects.equals(id, input.getId())) {
            throw new BadRequestException("Id from url does not match input!");
        }
        return ResponseEntity.ok(assembler.toModel(stateProvinceService.update(input)));
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('managers')")
    public StateProvince delete(@NotNull @PathVariable Long id) {
        return stateProvinceService.delete(id);
    }
}
