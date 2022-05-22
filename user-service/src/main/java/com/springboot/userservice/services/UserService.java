package com.springboot.userservice.services;

import java.util.List;

import com.springboot.userservice.entity.Address;
import com.springboot.userservice.entity.AppRole;
import com.springboot.userservice.entity.AppUser;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    AppUser getCurrentUser(String username);

    AppUser saveUser(AppUser user);

    AppRole saveRole(AppRole role);

    void addRoleToUser(String username, String role);

    List<AppUser> getUsers();

    void addAddressToUser(Integer Id, String username);

    void addAddressToUser(Address address, String username);
}
