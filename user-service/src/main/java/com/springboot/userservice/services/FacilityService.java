package com.springboot.userservice.services;

import java.util.List;

import com.springboot.userservice.entity.Address;
import com.springboot.userservice.entity.AppUser;
import com.springboot.userservice.entity.District;
import com.springboot.userservice.entity.Province;
import com.springboot.userservice.entity.Ward;

import org.springframework.stereotype.Service;

@Service
public interface FacilityService {
    Address getAddressByName(String name);

    Address saveAddress(Address address);

    Province saveProvince(Province province);

    District saveDistrict(District district);

    Ward saveWard(Ward ward);

    void addAddressToFacility(String username, String role);

    List<AppUser> getUsers();
}
