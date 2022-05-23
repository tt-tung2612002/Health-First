package com.springboot.userservice.services;

import java.util.List;

import com.springboot.userservice.dto.response.PlanResponseDto;
import com.springboot.userservice.entity.Plan;

import org.springframework.stereotype.Service;

@Service
public interface PlanService {

    public Plan savePlan(Plan plan);

    public List<PlanResponseDto> getAllPlans();

    public Plan getPlanById(Integer id);

}