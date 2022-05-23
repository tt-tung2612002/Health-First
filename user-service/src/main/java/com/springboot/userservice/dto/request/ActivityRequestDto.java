package com.springboot.userservice.dto.request;

import lombok.Data;

@Data
public class ActivityRequestDto {

    private String name;

    private String startDate;

    private String endDate;

    private String conclusion;

    private Integer activityStateId;

    private Integer activityResultId;

    private Integer createdUserId;

    private Integer facilityId;

    private Integer planId;

}