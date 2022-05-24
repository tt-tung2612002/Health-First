package com.springboot.userservice.controllers;

import java.net.URI;
import java.util.List;

import com.springboot.userservice.dto.request.FacilityRequestDto;
import com.springboot.userservice.dto.response.FacilityResponseDto;
import com.springboot.userservice.entity.Address;
import com.springboot.userservice.entity.Facility;
import com.springboot.userservice.entity.FacilityState;
import com.springboot.userservice.entity.Ward;
import com.springboot.userservice.services.FacilityService;
import com.springboot.userservice.services.UserService;
import com.springboot.userservice.utils.JwtTokenUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    private final UserService userService;

    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping("/list")
    public ResponseEntity<List<FacilityResponseDto>> getAllFacility(
            @RequestHeader(name = "Authorization") String userToken) {
        userToken = userToken.substring("Bearer ".length() + JwtTokenUtils.preToken.length());
        String username = jwtTokenUtils.getUsernameFromToken(userToken);
        return ResponseEntity.ok()
                .body(
                        facilityService.getAllFacilityByUser(userService.getCurrentUserByName(username)));
    }

    @PostMapping("/create")
    public ResponseEntity<?> addFacility(@RequestBody FacilityRequestDto facilityDto) {
        // facilityService.addFacility(payload);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/facilities/create")
                        .toUriString());
        Facility facility = new Facility();
        facility.setName(facilityDto.getName());
        facility.setFacilityState(facilityService.getFacilityStateById(facilityDto.getFacilityStateId()));
        Address address = new Address();
        address.setName(facilityDto.getAddress());
        // address.setWard(facilityService.getWardById());
        facilityService.saveAddress(address, facilityService.getWardById(facilityDto.getWardId()));

        facility.setAddress(address);

        FacilityState facilityState = facilityService.getFacilityStateById(facilityDto.getFacilityStateId());

        facility.setFacilityState(facilityState);

        facility.setBusinessType(facilityService.getBusinessTypeById(facilityDto.getBusinessTypeId()));
        // facility.setAddress();
        facilityService.saveFacility(facility);
        return ResponseEntity.created(uri).body("Facility created successfully");
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateFacility(@RequestBody FacilityRequestDto facilityDto) {
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/facilities/update")
                        .toUriString());

        // check if facility exist.
        Facility facility = facilityService.getFacilityById(facilityDto.getId());
        if (facility == null) {
            return ResponseEntity.badRequest().body("Facility with id " + facilityDto.getId() + " not found");
        }

        String name = facilityDto.getName();
        if (name != null)
            facility.setName(name);

        if (facilityDto.getFacilityStateId() != null) {
            facility.setFacilityState(facilityService.getFacilityStateById(facilityDto.getFacilityStateId()));
        }

        if (facilityDto.getAddress() != null) {
            Address address = new Address();
            address.setName(facilityDto.getAddress());
            Ward ward = facilityService.getWardById(facilityDto.getWardId());
            if (ward == null)
                return ResponseEntity.badRequest()
                        .body("Can not update facility with id " + facilityDto.getId() + ". Ward not found");
            facilityService.saveAddress(address, ward);
            facility.setAddress(address);
        }

        if (facilityDto.getBusinessTypeId() != null) {
            facility.setBusinessType(facilityService.getBusinessTypeById(facilityDto.getBusinessTypeId()));
        }

        facilityService.saveFacility(facility);

        return ResponseEntity.created(uri).body("Facility updated successfully");
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteFacility(@RequestBody FacilityRequestDto facilityDto) {
        facilityService.deleteFacility(facilityDto.getId());
        return ResponseEntity.ok().body("Facility deleted successfully");
    }

}