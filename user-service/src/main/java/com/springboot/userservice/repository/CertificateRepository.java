package com.springboot.userservice.repository;

import com.springboot.userservice.entity.Certificate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Certificate findById(Integer id);

    Certificate findByCertificateNumber(String number);

    Long deleteByCertificateNumber(String name);

    Long deleteById(Integer id);
}
