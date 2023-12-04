package com.example.customerservice.services.customer;

import com.example.customerservice.entities.customer.Customer;
import com.example.customerservice.entities.customer.Customer_;
import com.example.customerservice.repositories.customer.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Customer input) {
        Customer customer = customerRepository.findById(input.getId())
                .orElseThrow(EntityNotFoundException::new);
        BeanUtils.copyProperties(input, customer);
        return customerRepository.save(customer);
    }

    public Customer delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        customerRepository.delete(customer);
        return customer;
    }

    public Customer get(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Customer> listAll() {
        return customerRepository.findAll(Sort.by(Customer_.LAST_NAME, Customer_.FIRST_NAME));
    }
}
