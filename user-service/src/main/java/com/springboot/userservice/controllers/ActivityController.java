package com.springboot.userservice.controllers;

import java.net.URI;
import java.sql.Date;
import java.util.List;

import com.springboot.userservice.dto.request.ActivityRequestDto;
import com.springboot.userservice.dto.response.ActivityResponseDto;
import com.springboot.userservice.entity.Activity;
import com.springboot.userservice.services.ActivityService;
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
@RequestMapping(path = "/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    private final UserService userService;

    private final FacilityService facilityService;

    @GetMapping("/list")
    public ResponseEntity<List<ActivityResponseDto>> getAllActivities() {
        return ResponseEntity.ok().body(activityService.getAllActivities());
    }

    @PostMapping("/create")
    public ResponseEntity<?> addActivity(@RequestBody ActivityRequestDto activityDto) {
        // facilityService.addFacility(payload);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/activities/create")
                        .toUriString());
        Activity activity = new Activity();

        Date startDate = Date.valueOf(activityDto.getStartDate());
        Date endDate = Date.valueOf(activityDto.getEndDate());
        activity.setName(activityDto.getName());

        activity.setConclusion(activityDto.getConclusion());

        activity.setActivityState(activityService.getActivityStateById(activityDto.getActivityStateId()));

        // activity.setFacility(facilityService.getFacilityById(activityDto.getFacilityId()));

        activity.setCreatedUser(userService.getCurrentUserById(activityDto.getCreatedUserId()));

        activity.setStartDate(startDate);
        activity.setEndDate(endDate);

        activityService.saveActivity(activity);

        return ResponseEntity.created(uri).build();
    }

}