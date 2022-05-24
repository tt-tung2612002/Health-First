package com.springboot.userservice.services;

import java.util.List;

import com.springboot.userservice.dto.response.SampleResponseDto;
import com.springboot.userservice.entity.Sample;
import com.springboot.userservice.entity.SampleResult;
import com.springboot.userservice.entity.SampleState;

import org.springframework.stereotype.Service;

@Service
public interface SampleService {

    public Sample saveSample(Sample sample);

    public Sample getSampleById(Integer id);

    public List<SampleResponseDto> getAllSamples();

    public Long deleteSampleById(Integer id);

    public SampleResult getSampleResultById(Integer id);

    public SampleState getSampleStateById(Integer id);

}