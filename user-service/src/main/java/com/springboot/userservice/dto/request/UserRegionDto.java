package com.springboot.userservice.dto.request;

import lombok.Data;

@Data
public class UserRegionDto {
    private String username;
    private Integer wardId;
}