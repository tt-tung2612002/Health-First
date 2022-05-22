package com.springboot.userservice.repository;

import com.springboot.userservice.entity.Plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    Plan findById(Integer Id);

    Plan findByName(String name);

    Long deleteById(Integer Id);

}