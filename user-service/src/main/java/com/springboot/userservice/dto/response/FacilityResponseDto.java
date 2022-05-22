package com.springboot.userservice.dto.response;

import com.springboot.userservice.entity.Facility;

import lombok.Data;

@Data
public class FacilityResponseDto {

    private Integer id;

    private String facilityCode;

    private String name;

    private String FacilityState;

    private String businessType;

    public FacilityResponseDto(Facility facility) {
        this.id = facility.getId();
        this.facilityCode = facility.getFacilityCode();
        this.name = facility.getName();
        this.FacilityState = facility.getFacilityState().getName();
        this.businessType = facility.getBusinessType().getName();
    }

}