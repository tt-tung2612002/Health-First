package com.springboot.userservice.services;

import java.util.List;

import javax.transaction.Transactional;

import com.springboot.userservice.entity.Activity;
import com.springboot.userservice.repository.ActivityRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    @Override
    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public List<Activity> getActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Activity getActivityById(Integer id) {
        return activityRepository.findById(id);
    }

}