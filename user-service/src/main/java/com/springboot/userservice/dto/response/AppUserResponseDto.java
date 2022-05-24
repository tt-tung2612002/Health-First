package com.springboot.userservice.dto.response;

import com.springboot.userservice.entity.AppUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserResponseDto {

    private String displayName;

    private String username;

    private String email;

    public AppUserResponseDto(AppUser appUser) {
        this.displayName = appUser.getDisplayName();
        this.username = appUser.getUsername();
        this.email = appUser.getEmail();
    }
}