package com.springboot.userservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserRequest {
    private String username;

    private String password;

    private String email;

    private String displayName;
}
