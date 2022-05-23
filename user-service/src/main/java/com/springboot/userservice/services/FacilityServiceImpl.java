package com.springboot.userservice.services;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.springboot.userservice.dto.request.CertificateRequestDto;
import com.springboot.userservice.dto.response.CertificateResponseDto;
import com.springboot.userservice.dto.response.FacilityResponseDto;
import com.springboot.userservice.entity.Address;
import com.springboot.userservice.entity.AppUser;
import com.springboot.userservice.entity.BusinessType;
import com.springboot.userservice.entity.Certificate;
import com.springboot.userservice.entity.CertificateState;
import com.springboot.userservice.entity.District;
import com.springboot.userservice.entity.Facility;
import com.springboot.userservice.entity.FacilityState;
import com.springboot.userservice.entity.Province;
import com.springboot.userservice.entity.Ward;
import com.springboot.userservice.repository.AddressRepository;
import com.springboot.userservice.repository.BusinessTypeRepository;
import com.springboot.userservice.repository.CertificateRepository;
import com.springboot.userservice.repository.CertificateStateRepository;
import com.springboot.userservice.repository.DistrictRepository;
import com.springboot.userservice.repository.FacilityRepository;
import com.springboot.userservice.repository.FacilityStateRepository;
import com.springboot.userservice.repository.ProvinceRepository;
import com.springboot.userservice.repository.WardRepository;

import org.springframework.stereotype.Service;

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
    private final CertificateStateRepository certificateStateRepository;
    private final CertificateRepository certificateRepository;

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

    @Override
    public Facility saveFacility(Facility facility) {
        return facilityRepository.save(facility);
    }

    @Override
    public Facility getFacilityById(Integer id) {
        return facilityRepository.findById(id);
    }

    @Override
    public CertificateState getCertificateStateByName(String name) {
        return certificateStateRepository.findByName(name);
    }

    @Override
    public int saveCertificate(CertificateRequestDto certificate) {

        return certificateRepository.saveCertificate(certificate.getFacilityId(), certificate.getCertificateNumber(),
                Date.valueOf(certificate.getPublishedDate()), Date.valueOf(certificate.getExpiredDate()));
    }

    @Override
    public List<FacilityResponseDto> getAllFacilityByUser(AppUser user) {

        List<Facility> facilities = facilityRepository.getFacilitiesByUser(user.getId());
        return facilities.stream()
                .map(FacilityResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<CertificateResponseDto> getAllCertificateByUser(int id) {
        List<Certificate> certificates = certificateRepository.findAllById(id);
        return certificates.stream()
                .map(CertificateResponseDto::new)
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
    public Certificate getCertificateByNumber(String number) {
        return certificateRepository.findByCertificateNumber(number);
    }

    @Override
    public Long deleteCertificateById(Integer id) {
        return certificateRepository.deleteById(id);
    }

    @Override
    public Long deleteCertificateByNumber(String number) {
        return certificateRepository.deleteByCertificateNumber(number);
    }

    @Override
    public FacilityState getFacilityStateById(Integer id) {
        return facilityStateRepository.findById(id);
    }

}