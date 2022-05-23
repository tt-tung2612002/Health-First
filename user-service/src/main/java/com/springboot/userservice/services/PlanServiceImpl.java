package com.springboot.userservice.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.springboot.userservice.dto.response.PlanResponseDto;
import com.springboot.userservice.entity.Plan;
import com.springboot.userservice.repository.PlanRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    @Override
    public Plan savePlan(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public List<PlanResponseDto> getAllPlans() {
        return planRepository.findAll().stream().map(PlanResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public Plan getPlanById(Integer id) {
        return planRepository.findById(id);
    }

}