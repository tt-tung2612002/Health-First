package com.springboot.userservice.services;

import com.springboot.userservice.entity.Address;
import com.springboot.userservice.entity.BusinessType;
import com.springboot.userservice.entity.Certificate;
import com.springboot.userservice.entity.CertificateState;
import com.springboot.userservice.entity.District;
import com.springboot.userservice.entity.Facility;
import com.springboot.userservice.entity.FacilityState;
import com.springboot.userservice.entity.Province;
import com.springboot.userservice.entity.Ward;

import org.springframework.stereotype.Service;

@Service
public interface FacilityService {
    // Address getAddressByName(String name);
    public Address getAddressById(Integer Id);

    public Address saveAddress(Address address, Ward ward);

    public Province getProvinceById(Integer id);

    public Province saveProvince(Province province);

    public District saveDistrictByProvince(District district, Province province);

    public Ward saveWardByDistrict(Ward ward, District district);

    public Ward getWardById(Integer i);

    public Facility saveFacility(Facility facility);

    public Facility findFacilityById(Integer id);

    public FacilityState getFacilityStateByName(String name);

    public CertificateState getCertificateStateByName(String name);

    public Certificate saveCertificate(Certificate certificate);

    public BusinessType getBusinessTypeById(Integer id);

}
