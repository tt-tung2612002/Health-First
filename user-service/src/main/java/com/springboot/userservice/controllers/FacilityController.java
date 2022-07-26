package com.springboot.userservice.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.gson.Gson;
import com.springboot.userservice.dto.request.FacilityRequestDto;
import com.springboot.userservice.dto.request.SearchFilterRequest;
import com.springboot.userservice.dto.response.BaseResponse;
import com.springboot.userservice.dto.response.FacilityResponseDto;
import com.springboot.userservice.entity.Facility;
import com.springboot.userservice.services.FacilityService;
import com.springboot.userservice.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    private final UserService userService;

    @PostMapping("/list")
    public ResponseEntity<?> getAllFacility(
            @RequestHeader(name = "Authorization") String userToken,
            @RequestBody SearchFilterRequest searchFilterRequest) {

        searchFilterRequest.setUserId(userService.getUserByToken(userToken).getId());

        List<FacilityResponseDto> filteredResponse = facilityService
                .getAllFacilityWithFilter(new Gson().toJson(searchFilterRequest));

        return ResponseEntity.ok()
                .body(new BaseResponse("1", "success", filteredResponse));

    }

    @PostMapping("/create")
    public ResponseEntity<?> addFacility(@RequestBody FacilityRequestDto facilityDto) {
        // facilityService.addFacility(payload);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/facilities/create")
                        .toUriString());

        Facility facility = facilityService.saveFacility(facilityDto, false);

        if (facility == null) {
            return ResponseEntity.badRequest().body(new BaseResponse("0", "Failed to create facility", ""));
        }

        BaseResponse response = new BaseResponse("1", "Facility created successfully", "");
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateFacility(@RequestBody FacilityRequestDto facilityDto) {
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/facilities/update")
                        .toUriString());

        Facility facility = facilityService.saveFacility(facilityDto, true);

        if (facility == null) {
            return ResponseEntity.badRequest().body(new BaseResponse("0", "Failed to update facility", ""));
        }

        BaseResponse response = new BaseResponse("1", "Facility updated successfully", "");

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteFacility(@RequestBody FacilityRequestDto facilityDto) {
        facilityService.deleteFacility(facilityDto.getId());
        return ResponseEntity.ok().body(new BaseResponse("1", "Facility deleted successfully", ""));
    }

}