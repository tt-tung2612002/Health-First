package com.springboot.userservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.userservice.dto.request.SampleRequestDto;
import com.springboot.userservice.dto.request.SearchFilterRequest;
import com.springboot.userservice.dto.response.SampleResponseDto;
import com.springboot.userservice.entity.Sample;
import com.springboot.userservice.entity.SampleResult;
import com.springboot.userservice.entity.SampleState;

@Service
public interface SampleService {

    public Sample saveSample(SampleRequestDto sampleRequestDto, Boolean isUpdate);

    public Sample getSampleById(Integer id);

    public Sample getLastSample();

    public List<SampleResponseDto> getAllSamplesWithFilter(String userToken, SearchFilterRequest searchFilterRequest);

    public Long deleteSampleById(Integer id);

    public SampleResult getSampleResultById(Integer id);

    public SampleState getSampleStateById(Integer id);

}