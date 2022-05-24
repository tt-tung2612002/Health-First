package com.springboot.userservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanRequestDto {

    private Integer id;

    private String name;

    private String description;

    private Integer planStateId;

    private String publishedDate;

    private Integer createdUserId;

}