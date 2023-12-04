package com.example.customerservice.repositories.customer;

import com.example.customerservice.entities.customer.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}