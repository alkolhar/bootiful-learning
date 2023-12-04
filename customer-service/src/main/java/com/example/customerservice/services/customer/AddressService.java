package com.example.customerservice.services.customer;

import com.example.customerservice.entities.customer.Address;
import com.example.customerservice.entities.customer.Address_;
import com.example.customerservice.repositories.customer.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public Address create(Address address) {
        return addressRepository.save(address);
    }

    public Address update(Address input) {
        Address country = addressRepository.findById(input.getId())
                .orElseThrow(EntityNotFoundException::new);
        BeanUtils.copyProperties(input, country);
        return addressRepository.save(country);
    }

    public Address delete(Long id) {
        Address country = addressRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        addressRepository.delete(country);
        return country;
    }

    public Address get(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Address> listAll() {
        return addressRepository.findAll(Sort.by(Address_.POSTAL_CODE));
    }
}
