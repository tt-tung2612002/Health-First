package com.springboot.userservice.controllers;

import java.net.URI;
import java.sql.Date;

import com.springboot.userservice.dto.request.AppUserRequestDto;
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
    public ResponseEntity<?> saveUser(@RequestBody AppUserRequestDto appUserRequestDto) {
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/create").toUriString());

        // check if user exist in database.
        AppUser appUser = userService.getCurrentUserByName(appUserRequestDto.getUsername());

        if (appUser != null) {
            return ResponseEntity.badRequest().body(new BaseResponse("0", "User already exists", ""));
        }

        appUser = new AppUser();
        appUser.setCreatedDate(new Date(System.currentTimeMillis()));

        appUser.setUsername(appUserRequestDto.getUsername());
        appUser.setPassword(appUserRequestDto.getPassword());
        appUser.setDisplayName(appUserRequestDto.getDisplayName());
        appUser.setPhoneNumber(appUserRequestDto.getPhoneNumber());
        appUser.setEmail(appUserRequestDto.getEmail());
        userService.saveUser(appUser);

        for (int roleId : appUserRequestDto.getRoles()) {
            userService.addRoleToUser(appUser.getUsername(), roleId);
        }

        AppUserResponseDto userResponseDto = new AppUserResponseDto(appUser);

        BaseResponse baseResponse = new BaseResponse(userResponseDto == null ? "0" : "1",
                userResponseDto == null ? "Can't create user" : "User created successfully", userResponseDto);
        return ResponseEntity.created(uri).body(baseResponse);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody AppUserRequestDto appUserRequestDto) {
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/create").toUriString());

        // check if user exist in database.
        AppUser appUser = userService.getCurrentUserByName(appUserRequestDto.getUsername());

        if (appUser == null) {
            return ResponseEntity.badRequest().body(
                    new BaseResponse("0", "Can't find user with username " + appUserRequestDto.getUsername(), ""));
        }

        if (appUserRequestDto.getDisplayName() != null) {
            appUser.setDisplayName(appUserRequestDto.getDisplayName());
        }
        if (appUserRequestDto.getPhoneNumber() != null)
            appUser.setPhoneNumber(appUserRequestDto.getPhoneNumber());

        if (appUserRequestDto.getEmail() != null)
            appUser.setEmail(appUserRequestDto.getEmail());

        userService.saveUser(appUser);

        for (int roleId : appUserRequestDto.getRoles()) {
            userService.addRoleToUser(appUser.getUsername(), roleId);
        }
        AppUserResponseDto userResponseDto = new AppUserResponseDto(appUser);

        BaseResponse baseResponse = new BaseResponse(userResponseDto == null ? "0" : "1",
                userResponseDto == null ? "Can't create user" : "User created successfully", userResponseDto);
        return ResponseEntity.created(uri).body(baseResponse);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody AppUserRequestDto appUserRequestDto) {
        AppUser appUser = null;
        if (appUserRequestDto.getId() != null) {
            appUser = userService.getCurrentUserById(appUserRequestDto.getId());
        }
        if (appUserRequestDto.getUsername() != null) {
            appUser = userService.getCurrentUserByName(appUserRequestDto.getUsername());
        }
        if (appUser == null) {
            return ResponseEntity.badRequest().body(new BaseResponse("0", "Can't find user", ""));
        }

        userService.deleteUserById(appUser.getId());
        return ResponseEntity.ok().body(new BaseResponse("1", "User deleted successfully", ""));
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

    @PostMapping("/region/removeFromUser")
    public ResponseEntity<?> removeRoleFromUser(@RequestBody UserRoleDto payload) {
        return ResponseEntity.ok().body(new BaseResponse("1", "Region added to user successfully", ""));
    }

    @PostMapping("/region/addToUser")
    public ResponseEntity<?> addRegionToUser(@RequestBody UserRegionDto payload) {
        userService.addRegionToUser(payload.getWardId(), payload.getUsername());
        return ResponseEntity.ok().body(new BaseResponse("1", "Region added to user successfully", ""));
    }

}
