package com.springboot.userservice.services;

import java.util.List;

import com.springboot.userservice.dto.response.CertificateResponseDto;
import com.springboot.userservice.entity.Certificate;
import com.springboot.userservice.entity.CertificateState;

/**
 * CertificateService
 */
public interface CertificateService {

    public Certificate saveCertificate(Certificate certificate);

    public List<CertificateResponseDto> getAllCertificateByUser(Integer userId);

    public Certificate getCertificateById(Integer id);

    public CertificateState getCertificateStateById(Integer id);

    public Long deleteCertificateById(Integer id);

}