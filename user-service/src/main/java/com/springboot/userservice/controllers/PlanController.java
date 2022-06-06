package com.springboot.userservice.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.userservice.dto.request.PlanRequestDto;
import com.springboot.userservice.dto.request.SearchFilterRequest;
import com.springboot.userservice.dto.response.BaseResponse;
import com.springboot.userservice.entity.Plan;
import com.springboot.userservice.services.PlanService;
import com.springboot.userservice.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    private final UserService userService;

    @PostMapping("/list")
    public ResponseEntity<?> getAllPlans(@RequestHeader("Authorization") String userToken,
            @RequestBody SearchFilterRequest searchFilterRequest) {

        searchFilterRequest.setUserId(userService.getUserByToken(userToken).getId());
        BaseResponse response = new BaseResponse("1", "Get plans successfully",
                planService.getAllPlans(searchFilterRequest));

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> addPlan(@RequestHeader(name = "Authorization") String userToken,
            @RequestBody PlanRequestDto planRequestDto) {

        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/plans/create")
                        .toUriString());

        Plan plan = planService.savePlan(userToken, planRequestDto, false);
        if (plan == null) {
            return ResponseEntity.badRequest().body(new BaseResponse("0", "Create plan failed", ""));
        }

        return ResponseEntity.created(uri).body(new BaseResponse("1", "Create plan successfully", ""));

    }

    @PostMapping("/update")
    public ResponseEntity<?> updatePlan(@RequestBody PlanRequestDto planRequestDto) {

        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/plans/update")
                        .toUriString());

        Plan plan = planService.savePlan(null, planRequestDto, true);

        if (plan == null) {
            return ResponseEntity.badRequest().body(new BaseResponse("0", "Update plan failed", ""));
        }

        return ResponseEntity.created(uri).body(new BaseResponse("1", "Update plan successfully", ""));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteFacility(@RequestBody PlanRequestDto planRequestDto) {
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/plans/delete")
                        .toUriString());
        planService.deletePlanById(planRequestDto.getId());

        return ResponseEntity.created(uri).body(new BaseResponse("1", "Delete plan successfully", ""));
    }

    @PostMapping("/active")
    public ResponseEntity<?> activatePlan(@RequestHeader(name = "Authorization") String userToken,
            @RequestBody PlanRequestDto planRequestDto) {
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/plans/active")
                        .toUriString());

        Plan plan = planService.getPlanById(planRequestDto.getId());
        if (plan == null) {
            return ResponseEntity.badRequest().body(new BaseResponse("0", "Plan not found", ""));
        }

        planService.activatePlan(planRequestDto.getId());

        return ResponseEntity.created(uri).body(new BaseResponse("1", "Active plan successfully", ""));
    }

}