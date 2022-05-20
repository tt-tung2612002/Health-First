package com.springboot.userservice.services;

import java.util.List;

import com.springboot.userservice.dto.response.DistrictResponseDto;
import com.springboot.userservice.dto.response.WardResponseDto;
import com.springboot.userservice.entity.Province;
import com.springboot.userservice.entity.Ward;

/**
 * StaticDataService
 */
public interface StaticDataService {

    public List<Province> getProvinces();

    public List<DistrictResponseDto> getDistrictsByProvince(int provinceId);

    public List<WardResponseDto> getWardsByDistrict(int districtId);
    // public District getDistrictById(int districtId);

    public List<Ward> getWardsByDistrict();

}