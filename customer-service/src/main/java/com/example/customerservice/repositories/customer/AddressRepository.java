package com.example.customerservice.repositories.customer;

import com.example.customerservice.entities.customer.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}