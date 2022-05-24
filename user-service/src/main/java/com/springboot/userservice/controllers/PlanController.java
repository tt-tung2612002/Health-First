package com.springboot.userservice.controllers;

import java.net.URI;
import java.sql.Date;
import java.util.List;

import com.springboot.userservice.dto.request.PlanRequestDto;
import com.springboot.userservice.dto.response.PlanResponseDto;
import com.springboot.userservice.entity.Plan;
import com.springboot.userservice.entity.PlanState;
import com.springboot.userservice.services.PlanService;
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
@RequestMapping(path = "/api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<PlanResponseDto>> getAllPlans() {
        return ResponseEntity.ok().body(planService.getAllPlans());
    }

    @PostMapping("/create")
    public ResponseEntity<?> addPlan(@RequestBody PlanRequestDto planRequestDto) {

        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/plans/create")
                        .toUriString());

        Plan plan = new Plan();

        // set name.
        plan.setName(planRequestDto.getName());

        // set created user.
        plan.setCreatedUser(userService.getCurrentUserById(planRequestDto.getCreatedUserId()));

        // set created date.
        plan.setPublishedDate(Date.valueOf(planRequestDto.getPublishedDate()));

        // set description.
        plan.setDescription(planRequestDto.getDescription());

        // set plan state.
        plan.setPlanState(planService.getPlanStateById(planRequestDto.getPlanStateId()));

        planService.savePlan(plan);

        return ResponseEntity.created(uri).body("Created plan successfully.");
    }

    @PostMapping("/update")
    public ResponseEntity<?> updatePlan(@RequestBody PlanRequestDto planRequestDto) {

        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/plans/create")
                        .toUriString());

        Plan plan = planService.getPlanById(planRequestDto.getId());
        if (plan == null) {
            // return a error message.
            return ResponseEntity.badRequest().body("Plan with id " + planRequestDto.getId() + " not found.");
        }

        // set name if exist.
        String name = planRequestDto.getName();
        if (name != null)
            plan.setName(name);

        Date publishedDate = Date.valueOf(planRequestDto.getPublishedDate());
        if (publishedDate != null)
            plan.setPublishedDate(publishedDate);

        // set description if exist.
        String description = planRequestDto.getDescription();
        if (description != null)
            plan.setDescription(description);

        // set plan state if exist.
        PlanState planState = planService.getPlanStateById(planRequestDto.getPlanStateId());
        if (planState != null)
            plan.setPlanState(planState);

        planService.savePlan(plan);

        return ResponseEntity.created(uri).body("Updated plan successfully.");
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteFacility(@RequestBody PlanRequestDto planRequestDto) {
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/plans/delete")
                        .toUriString());
        planService.deletePlanById(planRequestDto.getId());

        return ResponseEntity.created(uri).body("Plan deleted successfully");
    }

}