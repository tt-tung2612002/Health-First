package com.springboot.userservice.repository;

import java.util.List;

import com.springboot.userservice.entity.Certificate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    @Query(value = "SELECT * FROM certificate WHERE user_id = ?1", nativeQuery = true)
    List<Certificate> findAllById(int id);

    Certificate findById(Integer id);

    Certificate findByCertificateNumber(String number);

    Long deleteByCertificateNumber(String name);

    Long deleteById(Integer id);
}
