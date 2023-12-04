package com.example.customerservice.repositories.customer;

import com.example.customerservice.entities.customer.StateProvince;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateProvinceRepository extends JpaRepository<StateProvince, Long> {
}