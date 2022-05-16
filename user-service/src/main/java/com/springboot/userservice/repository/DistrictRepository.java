package com.springboot.userservice.repository;

import com.springboot.userservice.entity.District;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
}
