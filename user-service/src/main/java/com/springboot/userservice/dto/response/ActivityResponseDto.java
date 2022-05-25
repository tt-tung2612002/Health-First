package com.springboot.userservice.dto.response;

import com.springboot.userservice.entity.Activity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityResponseDto {
    private Integer id;

    private String name;

    private String startDate;

    private String endDate;

    private String conclusion;

    private String activityState;

    private String activityResult;

    private String createdUser;

    private Integer facilityId;

    public ActivityResponseDto(Activity activity) {
        this.id = activity.getId();
        this.facilityId = activity.getFacility().getId();
        this.name = activity.getName();

        if (activity.getStartDate() != null)
            this.startDate = activity.getStartDate().toString();
        if (activity.getEndDate() != null)
            this.endDate = activity.getEndDate().toString();

        if (activity.getConclusion() != null)
            this.conclusion = activity.getConclusion();
        if (activity.getActivityState() != null)
            this.activityState = activity.getActivityState().getName();

        if (activity.getActivityResult() != null)
            this.activityResult = activity.getActivityResult().getName();

        this.createdUser = activity.getCreatedUser().getUsername();
    }

}