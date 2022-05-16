package com.springboot.userservice.repository;

import com.springboot.userservice.entity.Ward;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
}
