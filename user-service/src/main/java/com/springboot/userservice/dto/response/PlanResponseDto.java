package com.springboot.userservice.dto.response;

import com.springboot.userservice.entity.Plan;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanResponseDto {
    private Integer id;

    private String name;

    private Integer planStateId;

    private String publishedDate;

    private String createdUser;

    private Integer createdUserId;

    public PlanResponseDto(Plan plan) {
        this.id = plan.getId();
        this.name = plan.getName();
        this.planStateId = plan.getPlanState().getId();
        this.publishedDate = plan.getPublishedDate().toString();
        this.createdUser = plan.getCreatedUser().getUsername();
        this.createdUserId = plan.getCreatedUser().getId();
    }

}