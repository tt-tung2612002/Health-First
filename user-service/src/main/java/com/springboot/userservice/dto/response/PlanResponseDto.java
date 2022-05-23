package com.springboot.userservice.dto.response;

import com.springboot.userservice.entity.Plan;

import lombok.Data;

@Data
public class PlanResponseDto {
    private Integer id;

    private String name;

    private String planState;

    private String publishedDate;

    public PlanResponseDto(Plan plan) {
        this.id = plan.getId();
        this.name = plan.getName();
        this.planState = plan.getPlanState().getName();
        this.publishedDate = plan.getPublishedDate().toString();
    }

}