package com.springboot.userservice.dto.response;

import com.springboot.userservice.entity.Certificate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificateResponseDto {

    private Integer id;

    private String certificateNumber;

    private String publishedDate;

    private String expiredDate;

    private String certificateState;

    public CertificateResponseDto(Certificate certificate) {
        this.id = certificate.getId();
        this.certificateNumber = certificate.getCertificateNumber();
        this.publishedDate = certificate.getPublishedDate().toString();
        this.expiredDate = certificate.getExpiredDate().toString();
        this.certificateState = certificate.getCertificateState().getName();
    }

}