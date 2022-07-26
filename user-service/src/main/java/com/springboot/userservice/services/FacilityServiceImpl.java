package com.springboot.userservice.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.springboot.userservice.dto.request.FacilityRequestDto;
import com.springboot.userservice.dto.response.FacilityResponseDto;
import com.springboot.userservice.entity.Address;
import com.springboot.userservice.entity.AppUser;
import com.springboot.userservice.entity.BusinessType;
import com.springboot.userservice.entity.District;
import com.springboot.userservice.entity.Facility;
import com.springboot.userservice.entity.FacilityState;
import com.springboot.userservice.entity.Province;
import com.springboot.userservice.entity.Ward;
import com.springboot.userservice.repository.AddressRepository;
import com.springboot.userservice.repository.BusinessTypeRepository;
import com.springboot.userservice.repository.DistrictRepository;
import com.springboot.userservice.repository.FacilityRepository;
import com.springboot.userservice.repository.FacilityStateRepository;
import com.springboot.userservice.repository.ProvinceRepository;
import com.springboot.userservice.repository.WardRepository;

import lombok.RequiredArgsConstructor;

/**
 * FacilityServiceImpl
 */
@Service
@RequiredArgsConstructor
@Transactional
public class FacilityServiceImpl implements FacilityService {
    private final AddressRepository addressRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;
    private final FacilityStateRepository facilityStateRepository;
    private final BusinessTypeRepository businessTypeRepository;
    private final FacilityRepository facilityRepository;

    // public Address getAddressByName(String name) {

    // return addressRepository.findByName(name);
    // }
    public Address saveAddress(Address address, Ward ward) {

        // Ward ward = wardRepository.findByName(wardName);
        // address.setWardId(ward.getId());
        address.setWard(ward);
        return addressRepository.save(address);
    }

    public Province saveProvince(Province province) {
        return provinceRepository.save(province);
    }

    public District saveDistrictByProvince(District district, Province province) {
        // District district = districtRepository.findByName(districtName);
        province.getDistricts().add(district);
        district.setProvince(province);
        return districtRepository.save(district);
    }

    public Ward saveWardByDistrict(Ward ward, District district) {
        // Ward ward = wardRepository.findByName(wardName);
        district.getWards().add(ward);
        ward.setDistrict(district);
        return wardRepository.save(ward);
    }

    public Address getAddress(Integer Id) {
        return addressRepository.findById(Id);
    }

    @Override
    public Province getProvinceById(Integer id) {
        return provinceRepository.findById(id);
    }

    @Override
    public Ward getWardById(Integer i) {
        return wardRepository.findById(i);
    }

    @Override
    public Address getAddressById(Integer Id) {
        return addressRepository.findById(Id);
    }

    @Override
    public FacilityState getFacilityStateByName(String name) {
        return facilityStateRepository.findByName(name);
    }

    @Override
    public BusinessType getBusinessTypeById(Integer id) {
        return businessTypeRepository.findById(id);
    }

    public Facility updateFacility(FacilityRequestDto facilityRequestDto) {
        Facility facilityToUpdate = facilityRepository.findById(facilityRequestDto.getId());
        if (facilityToUpdate != null) {
            if (facilityRequestDto.getName() != null) {
                facilityToUpdate.setName(facilityRequestDto.getName());
            }

            if (facilityRequestDto.getFacilityStateId() != null) {
                facilityToUpdate
                        .setFacilityState(facilityStateRepository.findById(facilityRequestDto.getFacilityStateId()));
            }

            if (facilityRequestDto.getBusinessTypeId() != null) {
                facilityToUpdate
                        .setBusinessType(businessTypeRepository.findById(facilityRequestDto.getBusinessTypeId()));
            }

        }

        return facilityToUpdate;
    }

    @Override
    public Facility saveFacility(FacilityRequestDto facilityDto, boolean isUpdate) {

        if (isUpdate) {
            return updateFacility(facilityDto);
        }
        Facility facility = new Facility();

        facility.setName(facilityDto.getName());

        Facility lastFacility = getLastFacility();
        facility.setFacilityCode(Facility.FACILITY_CODE_PREFIX + String.valueOf(lastFacility.getId() + 1));

        if (facilityDto.getFacilityStateId() == null) {
            return null;
        }
        facility.setFacilityState(getFacilityStateById(facilityDto.getFacilityStateId()));

        if (facilityDto.getAddress() == null) {
            return null;
        }
        Address address = new Address();
        address.setName(facilityDto.getAddress());
        saveAddress(address, getWardById(facilityDto.getWardId()));
        facility.setAddress(address);

        // set facility state.
        if (facilityDto.getFacilityStateId() == null) {
            return null;
        }
        FacilityState facilityState = getFacilityStateById(facilityDto.getFacilityStateId());
        facility.setFacilityState(facilityState);

        if (facilityDto.getBusinessTypeId() == null) {
            return null;
        }
        facility.setBusinessType(getBusinessTypeById(facilityDto.getBusinessTypeId()));
        return facilityRepository.save(facility);
    }

    @Override
    public Facility getFacilityById(Integer id) {
        return facilityRepository.findById(id);
    }

    @Override
    public List<FacilityResponseDto> getAllFacilityByUser(AppUser user) {

        List<Facility> facilities = facilityRepository.getFacilitiesByUser(user.getId());
        return facilities.stream()
                .map(FacilityResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public BusinessType getBusinessTypeByName(String name) {
        return businessTypeRepository.findByName(name);
    }

    @Override
    public Long deleteFacility(Integer id) {
        return facilityRepository.deleteById(id);
    }

    @Override
    public FacilityState getFacilityStateById(Integer id) {
        return facilityStateRepository.findById(id);
    }

    @Override
    public Facility getLastFacility() {
        return facilityRepository.findTopByOrderByIdDesc();
    }

    @Override
    public List<FacilityResponseDto> getAllFacilityWithFilter(String filter) {

        List<Facility> facilities = facilityRepository.getFacilityWithFilter(filter);

        return facilities.stream()
                .map(FacilityResponseDto::new)
                .collect(Collectors.toList());
    }

}