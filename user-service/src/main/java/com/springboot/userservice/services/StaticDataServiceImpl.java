package com.springboot.userservice.services;

import java.util.List;
import java.util.stream.Collectors;

import com.springboot.userservice.dto.response.DistrictResponseDto;
import com.springboot.userservice.dto.response.WardResponseDto;
import com.springboot.userservice.entity.Province;
import com.springboot.userservice.entity.Ward;
import com.springboot.userservice.repository.DistrictRepository;
import com.springboot.userservice.repository.ProvinceRepository;
import com.springboot.userservice.repository.WardRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StaticDataServiceImpl implements StaticDataService {
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;

    public List<Province> getProvinces() {
        return provinceRepository.findAll();

    }

    public List<DistrictResponseDto> getDistrictsByProvince(int provinceId) {
        return districtRepository.findAllByProvince(provinceRepository.findById(provinceId)).stream()
                .map(district -> new DistrictResponseDto(district))
                .collect(Collectors.toList());

    }

    public List<Ward> getWardsByDistrict() {
        return null;
    }

    @Override
    public List<WardResponseDto> getWardsByDistrict(int districtId) {
        return wardRepository.findAllByDistrict(districtRepository.findById(districtId)).stream()
                .map(ward -> new WardResponseDto(ward))
                .collect(Collectors.toList());
    }

}