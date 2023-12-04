package com.example.userservice.repositories;

import com.example.userservice.entities.StateProvince;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateProvinceRepository extends JpaRepository<StateProvince, Long> {
}