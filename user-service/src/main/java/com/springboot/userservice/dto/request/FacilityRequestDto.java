package com.springboot.userservice.dto.request;

import lombok.Data;

@Data
public class FacilityRequestDto {
    private Integer id;

    private String facilityCode;

    private String name;

    private String FacilityState;

    private String businessType;

    private String address;

    private Integer wardId;

}