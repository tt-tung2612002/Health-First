package com.springboot.userservice.services;

import com.springboot.userservice.entity.AppRole;
import com.springboot.userservice.entity.AppUser;

import java.util.List;

public interface UserService {
    AppUser getUser(String username);

    AppUser saveUser(AppUser user);

    AppRole saveRole(AppRole role);

    void addRoleToUser(String username, String role);

    List<AppUser> getUsers();
}
