package com.springboot.userservice.services;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.springboot.userservice.dto.request.SampleRequestDto;
import com.springboot.userservice.dto.request.SearchFilterRequest;
import com.springboot.userservice.dto.response.SampleResponseDto;
import com.springboot.userservice.entity.Sample;
import com.springboot.userservice.entity.SampleResult;
import com.springboot.userservice.entity.SampleState;
import com.springboot.userservice.repository.SampleRepository;
import com.springboot.userservice.repository.SampleResultRepository;
import com.springboot.userservice.repository.SampleStateRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

    private final SampleRepository sampleRepository;

    private final SampleResultRepository sampleResultRepository;

    private final SampleStateRepository sampleStateRepository;

    private final UserService userService;

    private final ActivityService activityService;

    private final StaticDataService staticDataService;

    @Override
    public Sample saveSample(SampleRequestDto sampleRequestDto, Boolean isUpdate) {
        Sample sample = null;
        if (!isUpdate) {
            sample = new Sample();
        } else {
            sample = sampleRepository.findById(sampleRequestDto.getId());
        }

        // set sample code.
        if (!isUpdate) {
            Sample lastSample = getLastSample();
            sample.setSampleCode(Sample.SAMPLE_PREFIX + String.valueOf(lastSample.getId() + 1));

            // set created date.
            sample.setCreatedDate(new Date(System.currentTimeMillis()));

            if (activityService.getActivityById(sampleRequestDto.getActivityId()) == null) {
                return null;
            }
            sample.setActivity(activityService.getActivityById(sampleRequestDto.getActivityId()));

            if (staticDataService.getInspectionUnitById(sampleRequestDto.getInspectionUnitId()) == null) {
                return null;
            }
            sample.setInspectionUnit(staticDataService.getInspectionUnitById(sampleRequestDto.getInspectionUnitId()));
        }

        if (sampleRequestDto.getResultedDate() != null) {
            sample.setResultedDate(Date.valueOf(sampleRequestDto.getResultedDate()));
        }

        if (getSampleStateById(sampleRequestDto.getSampleStateId()) == null) {
            return null;
        }
        sample.setSampleState(getSampleStateById(sampleRequestDto.getSampleStateId()));

        if (getSampleResultById(sampleRequestDto.getSampleResultId()) == null) {
            return null;
        }
        sample.setSampleResult(getSampleResultById(sampleRequestDto.getSampleResultId()));

        if (staticDataService.getFoodById(sampleRequestDto.getFoodId()) == null) {
            return null;
        }
        sample.setFood(staticDataService.getFoodById(sampleRequestDto.getFoodId()));

        return sampleRepository.save(sample);
    }

    @Override
    public Sample getSampleById(Integer id) {
        return sampleRepository.findById(id);
    }

    @Override
    public List<SampleResponseDto> getAllSamplesWithFilter(String userToken, SearchFilterRequest searchFilterRequest) {

        searchFilterRequest.setUserId(userService.getUserByToken(userToken).getId());

        return sampleRepository.findAllSampleWithFilter(new Gson().toJson(searchFilterRequest)).stream()
                .map(SampleResponseDto::new).collect(Collectors.toList());
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

    @Override
    public Sample getLastSample() {
        return sampleRepository.findTopByOrderByIdDesc();
    }

}