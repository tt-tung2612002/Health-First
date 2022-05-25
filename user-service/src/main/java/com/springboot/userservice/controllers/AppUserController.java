package com.springboot.userservice.controllers;

import java.net.URI;

import com.springboot.userservice.dto.request.UserRegionDto;
import com.springboot.userservice.dto.request.UserRoleDto;
import com.springboot.userservice.dto.response.AppUserResponseDto;
import com.springboot.userservice.dto.response.BaseResponse;
import com.springboot.userservice.entity.AppRole;
import com.springboot.userservice.entity.AppUser;
import com.springboot.userservice.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class AppUserController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok().body(new BaseResponse("1", "success", userService.getUsers()));
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveUser(@RequestBody AppUser user) {
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/create").toUriString());

        // check if user exist in database.
        AppUser appUser = userService.getCurrentUserByName(user.getUsername());

        if (appUser != null) {
            return ResponseEntity.badRequest().body(new BaseResponse("0", "User already exist", ""));
        }

        user.setCreatedDate(new java.sql.Date(System.currentTimeMillis()));

        AppUserResponseDto userResponseDto = new AppUserResponseDto(userService.saveUser(user));

        BaseResponse baseResponse = new BaseResponse(userResponseDto == null ? "0" : "1",
                userResponseDto == null ? "Can't create user" : "User created successfully", userResponseDto);
        return ResponseEntity.created(uri).body(baseResponse);
    }

    @PostMapping("/role/create")
    public ResponseEntity<?> saveRole(@RequestBody AppRole role) {
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/role/create")
                        .toUriString());
        // return BaseResponse
        return ResponseEntity.created(uri).body(new BaseResponse("1", "Role created successfully",
                ""));
    }

    @PostMapping("/role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody UserRoleDto payload) {
        userService.addRoleToUser(payload.getUsername(), payload.getRoleId());
        return ResponseEntity.ok().body(new BaseResponse("1", "Role added to user successfully", ""));
    }

    @PostMapping("/region/addToUser")
    public ResponseEntity<?> addRegionToUser(@RequestBody UserRegionDto payload) {
        userService.addRegionToUser(payload.getWardId(), payload.getUsername());
        return ResponseEntity.ok().body(new BaseResponse("1", "Region added to user successfully", ""));
    }
}
