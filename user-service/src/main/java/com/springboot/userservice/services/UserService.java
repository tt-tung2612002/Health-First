package com.springboot.userservice.services;

import java.util.List;

import com.springboot.userservice.entity.AppRole;
import com.springboot.userservice.entity.AppUser;
import com.springboot.userservice.entity.Ward;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    AppUser getCurrentUser(String username);

    AppUser saveUser(AppUser user);

    AppRole saveRole(AppRole role);

    void addRoleToUser(String username, String role);

    List<AppUser> getUsers();

    void addRegionToUser(Integer Id, String username);

    void addRegionToUser(Ward ward, String username);
}
