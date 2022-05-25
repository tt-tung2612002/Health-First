package com.springboot.userservice.repository;

import com.springboot.userservice.entity.Sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {
    Sample findById(Integer Id);

    Sample findBySampleCode(String name);

    Sample findTopByOrderByIdDesc();

    Long deleteById(Integer Id);

    Long deleteBySampleCode(String name);

}