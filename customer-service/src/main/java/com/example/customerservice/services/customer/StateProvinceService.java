package com.example.customerservice.services.customer;

import com.example.customerservice.entities.customer.StateProvince;
import com.example.customerservice.repositories.customer.StateProvinceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateProvinceService {
    private final StateProvinceRepository stateProvinceRepository;

    public StateProvinceService(StateProvinceRepository stateProvinceRepository) {
        this.stateProvinceRepository = stateProvinceRepository;
    }


    public StateProvince create(StateProvince state) {
        return stateProvinceRepository.save(state);
    }

    public StateProvince update(StateProvince input) {
        StateProvince country = stateProvinceRepository.findById(input.getId())
                .orElseThrow(EntityNotFoundException::new);
        BeanUtils.copyProperties(input, country);
        return stateProvinceRepository.save(country);
    }

    public StateProvince delete(Long id) {
        StateProvince country = stateProvinceRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        stateProvinceRepository.delete(country);
        return country;
    }

    public StateProvince get(Long id) {
        return stateProvinceRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<StateProvince> listAll() {
        return stateProvinceRepository.findAll(Sort.by("abbreviation"));
    }
}
