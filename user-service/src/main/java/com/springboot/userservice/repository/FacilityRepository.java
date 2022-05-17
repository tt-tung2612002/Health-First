package com.springboot.userservice.repository;

import com.springboot.userservice.entity.Facility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    Facility findById(Integer Id);

    Facility findByName(String name);
}