package com.springboot.userservice.controllers;

import java.net.URI;
import java.util.List;

import com.springboot.userservice.dto.request.FacilityRequestDto;
import com.springboot.userservice.dto.response.FacilityResponseDto;
import com.springboot.userservice.entity.Address;
import com.springboot.userservice.entity.Facility;
import com.springboot.userservice.entity.FacilityState;
import com.springboot.userservice.services.FacilityService;
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
@RequestMapping(path = "/api/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    private final UserService userService;

    private String currentUser = AppUserController.currentUser;

    @GetMapping("/list")
    public ResponseEntity<List<FacilityResponseDto>> getAllFacility() {
        currentUser = AppUserController.currentUser;
        return ResponseEntity.ok()
                .body(facilityService.getAllFacilityByUser(userService.getCurrentUser(currentUser)));
    }

    @PostMapping("/create")
    public ResponseEntity<?> addFacility(@RequestBody FacilityRequestDto facilityDto) {
        // facilityService.addFacility(payload);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/facilities/create")
                        .toUriString());
        Facility facility = new Facility();
        facility.setName(facilityDto.getName());
        facility.setFacilityCode(facilityDto.getFacilityCode());
        Address address = new Address();
        address.setName(facilityDto.getAddress());
        // address.setWard(facilityService.getWardById());
        facilityService.saveAddress(address, facilityService.getWardById(facilityDto.getWardId()));

        facility.setAddress(address);
        FacilityState facilityState = new FacilityState();
        facilityState.setName(facilityDto.getFacilityState());

        facility.setFacilityState(facilityService.getFacilityStateByName(facilityDto.getFacilityState()));
        facility.setBusinessType(facilityService.getBusinessTypeByName(facilityDto.getBusinessType()));
        // facility.setAddress();
        facilityService.saveFacility(facility);
        return ResponseEntity.created(uri).body("Facility created successfully");
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteFacility(@RequestBody FacilityRequestDto facilityDto) {
        facilityService.deleteFacility(facilityDto.getId());
        return ResponseEntity.ok().body("Facility deleted successfully");
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateFacility(@RequestBody FacilityRequestDto facilityDto) {
        // facilityService.addFacility(payload);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/facilities/create")
                        .toUriString());
        Facility facility = facilityService.getFacilityById(facilityDto.getId());
        facility.setName(facilityDto.getName());
        facility.setFacilityCode(facilityDto.getFacilityCode());
        Address address = new Address();
        address.setName(facilityDto.getAddress());
        facilityService.saveAddress(address, facilityService.getWardById(facilityDto.getWardId()));

        facility.setAddress(address);
        FacilityState facilityState = new FacilityState();
        facilityState.setName(facilityDto.getFacilityState());

        facility.setFacilityState(facilityService.getFacilityStateByName(facilityDto.getFacilityState()));
        facility.setBusinessType(facilityService.getBusinessTypeByName(facilityDto.getBusinessType()));
        // facility.setAddress();
        facilityService.saveFacility(facility);
        return ResponseEntity.created(uri).body("Facility updated successfully");
    }
}