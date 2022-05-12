package com.springboot.userservice.dto.request;

import lombok.Data;

@Data
public class AppUserRequest {
    private String username;

    private String password;

    private String email;

    private String displayName;
}
