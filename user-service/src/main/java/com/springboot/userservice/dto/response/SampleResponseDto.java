package com.springboot.userservice.dto.response;

import com.springboot.userservice.entity.Sample;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleResponseDto {

    private String sampleCode;

    private String createdDate;

    private String resultedDate;

    private String sampleState;

    private String sampleResult;

    private Integer activityId;

    private Integer inspectionUnitId;

    private Integer foodId;

    public SampleResponseDto(Sample sample) {
        this.sampleCode = sample.getSampleCode();
        this.createdDate = sample.getCreatedDate().toString();
        if (sample.getResultedDate() != null) {
            this.resultedDate = sample.getResultedDate().toString();
        }
        this.sampleState = sample.getSampleState().getName();
        this.sampleResult = sample.getSampleResult().getDescription();
        this.activityId = sample.getActivity().getId();
        this.inspectionUnitId = sample.getInspectionUnit().getId();
        this.foodId = sample.getFood().getId();
    }

}