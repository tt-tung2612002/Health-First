package com.springboot.userservice.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class AppUserResponse {
    private String username;
    private String displayName;
    private String email;
    private List<String> roles;
}
