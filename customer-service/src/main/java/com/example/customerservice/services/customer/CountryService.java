package com.example.customerservice.services.customer;

import com.example.customerservice.entities.customer.Country;
import com.example.customerservice.repositories.customer.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    public Country create(Country customer) {
        return countryRepository.save(customer);
    }

    public Country update(Country input) {
        Country country = countryRepository.findById(input.getId())
                .orElseThrow(EntityNotFoundException::new);
        BeanUtils.copyProperties(input, country);
        return countryRepository.save(country);
    }

    public Country delete(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        countryRepository.delete(country);
        return country;
    }

    public Country get(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Country> listAll() {
        return countryRepository.findAll(Sort.by("abbreviation"));
    }
}
