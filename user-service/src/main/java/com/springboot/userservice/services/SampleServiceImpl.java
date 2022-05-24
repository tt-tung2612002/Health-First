package com.springboot.userservice.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.springboot.userservice.dto.response.SampleResponseDto;
import com.springboot.userservice.entity.Sample;
import com.springboot.userservice.entity.SampleResult;
import com.springboot.userservice.entity.SampleState;
import com.springboot.userservice.repository.SampleRepository;
import com.springboot.userservice.repository.SampleResultRepository;
import com.springboot.userservice.repository.SampleStateRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

    private final SampleRepository sampleRepository;

    private final SampleResultRepository sampleResultRepository;

    private final SampleStateRepository sampleStateRepository;

    @Override
    public Sample saveSample(Sample sample) {
        return sampleRepository.save(sample);
    }

    @Override
    public Sample getSampleById(Integer id) {
        return sampleRepository.findById(id);
    }

    @Override
    public List<SampleResponseDto> getAllSamples() {
        sampleRepository.findAll();
        return sampleRepository.findAll()
        .stream().map(SampleResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public Long deleteSampleById(Integer id) {
        return sampleRepository.deleteById(id);
    }

    @Override
    public SampleResult getSampleResultById(Integer id) {
        return sampleResultRepository.findById(id);
    }

    @Override
    public SampleState getSampleStateById(Integer id) {
        return sampleStateRepository.findById(id);
    }

}