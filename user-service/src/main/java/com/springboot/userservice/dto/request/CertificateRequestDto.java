package com.springboot.userservice.dto.request;

import lombok.Data;

@Data
public class CertificateRequestDto {

    private Integer facilityId;

    private String certificateNumber;

    private String publishedDate;

    private String expiredDate;

    private String certificateState;

}