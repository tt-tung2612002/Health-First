package com.springboot.userservice.repository;

import java.sql.Date;
import java.util.List;

import com.springboot.userservice.entity.Certificate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    @Query(value = "select * from certificate inner join facility on facility.id = certificate.facility_id inner join address on facility.address_id = address.id inner join user_region_management as u on u.address_id = address.id inner join user on user.id = u.user_id where user.id = ?1", nativeQuery = true)
    List<Certificate> findAllById(int id);

    @Procedure(procedureName = "saveCertificate")
    int saveCertificate(Integer facilityId,
            String certificateNumber, Date publishedDate, Date expiredDate);

    Certificate findById(Integer id);

    Certificate findByCertificateNumber(String number);

    Long deleteByCertificateNumber(String name);

    Long deleteById(Integer id);
}
