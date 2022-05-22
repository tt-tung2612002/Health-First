package com.springboot.userservice.dto.response;

import com.springboot.userservice.entity.Province;

import lombok.Data;

@Data
public class ProvinceResponseDto {
    private Integer id;
    private String name;

    public ProvinceResponseDto(Province province) {
        this.id = province.getId();
        this.name = province.getName();
    }
}