package com.springboot.userservice.controllers;

import java.util.List;

import com.springboot.userservice.dto.response.FacilityResponseDto;
import com.springboot.userservice.services.FacilityService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping("/list")
    public ResponseEntity<List<FacilityResponseDto>> getAllFacility() {
        return ResponseEntity.ok().body(facilityService.getAllFacility());
    }

}