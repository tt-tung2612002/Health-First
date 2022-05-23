package com.springboot.userservice.controllers;

import java.util.List;

import com.springboot.userservice.dto.response.PlanResponseDto;
import com.springboot.userservice.services.PlanService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @GetMapping("/list")
    public ResponseEntity<List<PlanResponseDto>> getAllPlans() {
        return ResponseEntity.ok().body(planService.getAllPlans());
    }

}