package com.springboot.userservice.dto.response;

import java.util.stream.Collectors;

import com.springboot.userservice.entity.AppUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserResponseDto {

    private String displayName;

    private String username;

    private String email;

    private String phoneNumber;

    private String roles;

    private String createdDate;

    public AppUserResponseDto(AppUser appUser) {
        this.displayName = appUser.getDisplayName();
        this.username = appUser.getUsername();
        this.email = appUser.getEmail();
        this.roles = appUser.getRoles().stream().map(role -> String.valueOf(role.getId()))
                .collect(Collectors.joining(","));
        this.phoneNumber = appUser.getPhoneNumber();
        this.createdDate = appUser.getCreatedDate().toString();
    }
}