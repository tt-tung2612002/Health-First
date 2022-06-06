package com.springboot.userservice.services;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.springboot.userservice.dto.request.PlanRequestDto;
import com.springboot.userservice.dto.request.SearchFilterRequest;
import com.springboot.userservice.dto.response.PlanResponseDto;
import com.springboot.userservice.entity.Activity;
import com.springboot.userservice.entity.Facility;
import com.springboot.userservice.entity.Plan;
import com.springboot.userservice.entity.PlanState;
import com.springboot.userservice.repository.PlanRepository;
import com.springboot.userservice.repository.PlanStateRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    private final PlanStateRepository planStateRepository;

    private final FacilityService facilityService;

    private final UserService userService;

    private final ActivityService activityService;

    @Override
    public Plan savePlan(String userToken, PlanRequestDto planRequestDto, boolean isUpdate) {

        if (isUpdate == true) {
            return updatePlan(planRequestDto);
        }
        Plan plan = new Plan();
        plan.setName(planRequestDto.getName());

        // set created user.

        plan.setCreatedUser(userService.getUserByToken(userToken));

        // set created date.
        plan.setPublishedDate(new Date(System.currentTimeMillis()));

        // set description.
        if (planRequestDto.getDescription() != null)
            plan.setDescription(planRequestDto.getDescription());

        // set plan state.
        plan.setPlanState(getPlanStateById(planRequestDto.getPlanStateId()));

        planRequestDto.getFacilityIds().stream().map(id -> facilityService.getFacilityById(id))
                .forEach(facility -> plan.getFacilities().add(facility));

        return planRepository.save(plan);
    }

    public Plan updatePlan(PlanRequestDto planRequestDto) {
        Plan plan = planRepository.findById(planRequestDto.getId());
        // set description.
        if (planRequestDto.getDescription() != null)
            plan.setDescription(planRequestDto.getDescription());

        // set plan state.
        if (planRequestDto.getPlanStateId() != null)
            plan.setPlanState(getPlanStateById(planRequestDto.getPlanStateId()));

        // update facilities of plan.
        if (planRequestDto.getFacilityIds() != null) {
            updateFacilityFromPlan(plan.getId(), planRequestDto.getFacilityIds());
        }

        return planRepository.save(plan);
    }

    @Override
    public List<PlanResponseDto> getAllPlans(SearchFilterRequest searchFilterRequest) {
        return planRepository.findAllPlanWithFilter(new Gson().toJson(searchFilterRequest))
                .stream().map(PlanResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public Plan getPlanById(Integer id) {
        return planRepository.findById(id);
    }

    @Override
    public PlanState getPlanStateById(Integer id) {
        return planStateRepository.findById(id);
    }

    @Override
    public int deletePlanById(Integer id) {
        return planRepository.deleteById(id);
    }

    @Override
    public void updateFacilityFromPlan(Integer planId, List<Integer> facilityIds) {

        Plan plan = planRepository.findById(planId);
        for (Integer facilityId : facilityIds) {
            Facility facility = facilityService.getFacilityById(facilityId);
            if (!plan.getFacilities().contains(facility)) {
                plan.getFacilities().add(facility);
            }
        }

        // remove all facilities except for the ones in the list
        List<Facility> facilities = plan.getFacilities().stream()
                .collect(Collectors.toList());
        for (Facility facility : facilities) {
            if (!facilityIds.contains(facility.getId())) {
                plan.getFacilities().remove(facility);
            }
        }

    }

    @Override
    public Plan activatePlan(Integer id) {
        Plan plan = planRepository.findById(id);

        Set<Facility> facilities = plan.getFacilities();

        // change state to activated.
        plan.setPlanState(getPlanStateById(1));

        facilities.stream().map(facility -> {
            Activity activity = new Activity();
            activity.setName(plan.getName() + "_" + facility.getName());
            activity.setCreatedDate(new Date(System.currentTimeMillis()));
            activity.setPlan(plan);
            activity.setCreatedUser(userService.getCurrentUserById(plan.getCreatedUser().getId()));
            activity.setActivityState(activityService.getActivityStateById(1));
            activity.setFacility(facility);
            return activity;
        }).forEach(activity -> activityService.saveActivity(activity));

        return plan;
    }

}