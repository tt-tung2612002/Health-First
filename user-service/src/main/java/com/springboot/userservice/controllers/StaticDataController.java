package com.springboot.userservice.controllers;

import java.util.List;

import com.springboot.userservice.dto.response.DistrictResponseDto;
import com.springboot.userservice.dto.response.ProvinceResponseDto;
import com.springboot.userservice.dto.response.WardResponseDto;
import com.springboot.userservice.entity.District;
import com.springboot.userservice.entity.Province;
import com.springboot.userservice.services.StaticDataService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/static/data")
@RequiredArgsConstructor
public class StaticDataController {

    private final StaticDataService staticDataService;

    @PostMapping("/provinces")
    public ResponseEntity<List<ProvinceResponseDto>> getProvinces() {
        return ResponseEntity.ok().body(staticDataService.getProvinces());
    }

    @PostMapping("/districts")
    public ResponseEntity<List<DistrictResponseDto>> getDistrictsByProvince(@RequestBody Province province) {
        return ResponseEntity.ok().body(staticDataService.getDistrictsByProvince(province.getId()));
    }

    @PostMapping("/wards")
    public ResponseEntity<List<WardResponseDto>> getWardsByDistrict(@RequestBody District district) {
        return ResponseEntity.ok().body(staticDataService.getWardsByDistrict(district.getId()));
    }
}