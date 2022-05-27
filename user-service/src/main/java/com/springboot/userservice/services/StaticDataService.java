package com.springboot.userservice.services;

import java.util.List;

import com.springboot.userservice.dto.response.DistrictResponseDto;
import com.springboot.userservice.dto.response.FoodResponseDto;
import com.springboot.userservice.dto.response.InspectionUnitResponseDto;
import com.springboot.userservice.dto.response.ProvinceResponseDto;
import com.springboot.userservice.dto.response.WardResponseDto;
import com.springboot.userservice.entity.Food;
import com.springboot.userservice.entity.InspectionUnit;

/**
 * StaticDataService
 */
public interface StaticDataService {

    public List<ProvinceResponseDto> getProvinces();

    public List<DistrictResponseDto> getDistrictsByProvince(int provinceId);

    public List<WardResponseDto> getWardsByDistrict(int districtId);

    public List<FoodResponseDto> getFoods();

    public List<InspectionUnitResponseDto> getInspectionUnits();

    public InspectionUnit getInspectionUnitById(int id);

    public Food getFoodById(int id);

}