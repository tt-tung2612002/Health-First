package com.springboot.userservice.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserResponse {
    private String username;
    private String displayName;
    private String email;
    private List<String> roles;
}
