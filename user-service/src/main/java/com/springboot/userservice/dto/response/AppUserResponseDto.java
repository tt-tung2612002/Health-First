package com.springboot.userservice.dto.response;

import com.springboot.userservice.entity.AppUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserResponseDto {
    private String username;
    private String displayName;
    private String email;

    public AppUserResponseDto(AppUser user) {
        this.username = user.getUsername();
        this.displayName = user.getDisplayName();
        this.email = user.getEmail();
    }
}
