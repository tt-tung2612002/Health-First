package com.springboot.userservice.dto.response;

import com.springboot.userservice.entity.Ward;

import lombok.Data;

@Data
public class WardResponseDto {
    private Integer id;
    private String name;

    public WardResponseDto(Ward ward) {
        this.id = ward.getId();
        this.name = ward.getName();
    }

}