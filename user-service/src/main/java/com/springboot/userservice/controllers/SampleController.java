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

import com.springboot.userservice.dto.request.SampleRequestDto;
import com.springboot.userservice.dto.request.SearchFilterRequest;
import com.springboot.userservice.dto.response.BaseResponse;
import com.springboot.userservice.dto.response.SampleResponseDto;
import com.springboot.userservice.entity.Sample;
import com.springboot.userservice.services.SampleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/activities/samples")
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @PostMapping("/list")
    public ResponseEntity<?> getAllSamples(@RequestHeader(name = "Authorization") String userToken,
            @RequestBody SearchFilterRequest searchFilterRequest) {

        List<SampleResponseDto> samples = sampleService.getAllSamplesWithFilter(userToken, searchFilterRequest);
        if (samples == null) {
            return ResponseEntity.badRequest().body(new BaseResponse("0", "No samples found", ""));
        }
        BaseResponse response = new BaseResponse("1", "Get all activities successfully",
                samples);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> addSample(@RequestHeader(name = "Authorization") String userToken,
            @RequestBody SampleRequestDto sampleRequestDto) {
        // facilityService.addFacility(payload);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/activities/samples/create")
                        .toUriString());

        Sample response = sampleService.saveSample(sampleRequestDto, false);
        if (response == null) {
            return ResponseEntity.badRequest().body(new BaseResponse("0", "Failed to create sample", ""));
        }
        return ResponseEntity.created(uri).body(new BaseResponse("1", "Create sample successfully", ""));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateSample(@RequestBody SampleRequestDto sampleRequestDto) {
        // facilityService.addFacility(payload);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/activities/samples/update")
                        .toUriString());

        Sample response = sampleService.saveSample(sampleRequestDto, true);

        if (response == null) {
            return ResponseEntity.badRequest().body(new BaseResponse("0", "Failed to update sample", ""));
        }

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteSample(@RequestBody SampleRequestDto sampleRequestDto) {
        // facilityService.addFacility(payload);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/activities/samples/delete")
                        .toUriString());

        Sample sample = sampleService.getSampleById(sampleRequestDto.getId());

        if (sample == null) {
            return ResponseEntity.badRequest()
                    .body(new BaseResponse("0", "No sample with id " + sampleRequestDto.getId() + " found!", ""));
        }

        sampleService.deleteSampleById(sample.getId());
        BaseResponse response = new BaseResponse("1", "Delete sample successfully", "");
        return ResponseEntity.created(uri).body(response);
    }

}