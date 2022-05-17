package com.springboot.userservice.services;

import com.springboot.userservice.entity.Address;
import com.springboot.userservice.entity.District;
import com.springboot.userservice.entity.Province;
import com.springboot.userservice.entity.Ward;

import org.springframework.stereotype.Service;

@Service
public interface FacilityService {
    // Address getAddressByName(String name);
    public Address getAddressById(Integer Id);

    public Province getProvinceById(Integer id);

    public Address saveAddress(Address address, Ward ward);

    public Province saveProvince(Province province);

    public District saveDistrictByProvince(District district, Province province);

    public Ward saveWardByDistrict(Ward ward, District district);

    public Ward getWardById(Integer i);

    // void addAddressToFacility(Address address, Facility facility);

}
