package com.example.customerservice.controllers.customer;

import com.example.customerservice.entities.customer.Country;
import com.example.customerservice.assemblers.customer.CountryRepresentationModelAssembler;
import com.example.customerservice.services.customer.CountryService;
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
@RequestMapping("country")
public class CountryController {

    private final CountryService countryService;
    private final CountryRepresentationModelAssembler assembler;

    public CountryController(CountryService countryService, CountryRepresentationModelAssembler assembler) {
        this.countryService = countryService;
        this.assembler = assembler;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('managers')")
    public ResponseEntity<EntityModel<Country>> create(@Valid @RequestBody Country country) {
        return ResponseEntity.ok(assembler.toModel(countryService.create(country)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<EntityModel<Country>> get(@NotNull @PathVariable Long id) {
        return ResponseEntity.ok(assembler.toModel(countryService.get(id)));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CollectionModel<EntityModel<Country>>> listAll() {
        return ResponseEntity.ok(
                assembler.toCollectionModel(countryService.listAll())
        );
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('managers')")
    public ResponseEntity<EntityModel<Country>> update(@PathVariable Long id, @Valid @RequestBody Country input) throws BadRequestException {
        if (!Objects.equals(id, input.getId())) {
            throw new BadRequestException("Id from url does not match input!");
        }
        return ResponseEntity.ok(assembler.toModel(countryService.update(input)));
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('managers')")
    public Country delete(@NotNull @PathVariable Long id) {
        return countryService.delete(id);
    }
}
