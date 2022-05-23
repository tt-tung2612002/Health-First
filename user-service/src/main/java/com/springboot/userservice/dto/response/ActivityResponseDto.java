package com.springboot.userservice.dto.response;

import com.springboot.userservice.entity.Activity;

import lombok.Data;

@Data
public class ActivityResponseDto {
    private Integer id;

    private String name;

    private String startDate;

    private String endDate;

    private String conclusion;

    private String activityState;

    private String activityResult;

    private String createdUser;

    public ActivityResponseDto(Activity activity) {
        this.id = activity.getId();
        this.name = activity.getName();
        this.startDate = activity.getStartDate().toString();
        this.endDate = activity.getEndDate().toString();
        this.conclusion = activity.getConclusion();
        this.activityState = activity.getActivityState().getName();
        this.activityResult = activity.getActivityResult().getName();
        this.createdUser = activity.getCreatedUser().getUsername();
    }

}