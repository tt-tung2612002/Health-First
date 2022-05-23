package com.springboot.userservice.services;

import java.util.List;

import com.springboot.userservice.entity.Activity;

import org.springframework.stereotype.Service;

@Service
public interface ActivityService {

    public Activity saveActivity(Activity activity);

    public List<Activity> getActivities();

    public Activity getActivityById(Integer id);

}