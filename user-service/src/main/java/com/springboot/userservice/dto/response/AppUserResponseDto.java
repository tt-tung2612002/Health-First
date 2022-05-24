package com.springboot.userservice.dto.response;

import java.util.List;
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

    private List<Integer> roles;

    private String createdDate;

    public AppUserResponseDto(AppUser appUser) {
        this.displayName = appUser.getDisplayName();
        this.username = appUser.getUsername();
        this.email = appUser.getEmail();
        // use built in function :: getId
        this.roles = appUser.getRoles().stream().map(role -> role.getId())
                .collect(Collectors.toList());
        this.phoneNumber = appUser.getPhoneNumber();
        this.createdDate = appUser.getCreatedDate().toString();
    }
}