package com.springboot.userservice.controllers;

import java.net.URI;
import java.sql.Date;

import com.springboot.userservice.dto.request.CertificateRequestDto;
import com.springboot.userservice.dto.response.BaseResponse;
import com.springboot.userservice.entity.Certificate;
import com.springboot.userservice.entity.Facility;
import com.springboot.userservice.services.CertificateService;
import com.springboot.userservice.services.FacilityService;
import com.springboot.userservice.services.UserService;
import com.springboot.userservice.utils.JwtTokenUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    private final FacilityService facilityService;

    private final JwtTokenUtils jwtTokenUtils;

    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<BaseResponse> getAllCertificate(
            @RequestHeader(name = "Authorization") String userToken) {
        userToken = userToken.substring("Bearer ".length() + JwtTokenUtils.preToken.length());
        String username = jwtTokenUtils.getUsernameFromToken(userToken);
        BaseResponse response = new BaseResponse("0", "success",
                certificateService.getAllCertificateByUser(
                        userService.getCurrentUserByName(username).getId()));
        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> addCertificateToFacility(
            @RequestBody CertificateRequestDto certificateDto) {
        // facilityService.addFacility(payload);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/certificates/create")
                        .toUriString());

        Certificate certificate = new Certificate();

        // set certificate number
        certificate.setCertificateNumber(certificateDto.getCertificateNumber());

        // // // convert string to SQL date.
        Date publishedDate = Date.valueOf(certificateDto.getPublishedDate());
        Date expiredDate = Date.valueOf(certificateDto.getExpiredDate());
        certificate.setPublishedDate(publishedDate);
        certificate.setExpiredDate(expiredDate);

        Facility facility = facilityService.getFacilityById(certificateDto.getFacilityId());

        certificate.setFacility(facility);

        // set certificate state.
        certificate.setCertificateState(certificateService.getCertificateStateById(
                certificateDto.getCertificateStateId()));

        Certificate result = certificateService.saveCertificate(certificate);
        BaseResponse response = new BaseResponse(result == null ? "0" : "-1",
                result != null ? "Add certificate success" : "Add certificate failed", "");
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCertificateToFacility(
            @RequestBody CertificateRequestDto certificateDto) {
        // facilityService.addFacility(payload);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/certificates/update")
                        .toUriString());

        Certificate certificate = certificateService.getCertificateById(certificateDto.getId());

        if (certificate == null) {
            BaseResponse response = new BaseResponse("0",
                    "Certificate with id " + certificateDto.getId() + " not found!", "");
            return ResponseEntity.created(uri).body(response);
        }
        // set certificate number
        if (certificateDto.getCertificateNumber() != null)
            certificate.setCertificateNumber(certificateDto.getCertificateNumber());

        // // // convert string to SQL date.
        if (certificateDto.getPublishedDate() != null) {
            Date publishedDate = Date.valueOf(certificateDto.getPublishedDate());
            certificate.setPublishedDate(publishedDate);
        }
        if (certificateDto.getExpiredDate() != null) {
            Date expiredDate = Date.valueOf(certificateDto.getExpiredDate());
            certificate.setExpiredDate(expiredDate);
        }

        if (certificateDto.getFacilityId() != null) {
            Facility facility = facilityService.getFacilityById(certificateDto.getFacilityId());
            certificate.setFacility(facility);
        }

        // set certificate state.
        if (certificateDto.getCertificateStateId() != null)
            certificate.setCertificateState(certificateService.getCertificateStateById(
                    certificateDto.getCertificateStateId()));

        Certificate result = certificateService.saveCertificate(certificate);
        BaseResponse response = new BaseResponse(result == null ? "0" : "-1",
                result != null ? "Update certificate success" : "Update certificate failed", "");
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCertificate(@RequestBody CertificateRequestDto certificateDto) {
        // facilityService.addFacility(payload);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/certificates/delete")
                        .toUriString());

        Certificate certificate = certificateService.getCertificateById(certificateDto.getId());

        if (certificate == null) {
            BaseResponse response = new BaseResponse("0",
                    "Certificate with id " + certificateDto.getId() + " not found!", "");
            return ResponseEntity.created(uri).body(response);
        }

        certificateService.deleteCertificateById(certificate.getId());
        return ResponseEntity.created(uri).body("Certificate deleted successfully");
    }
}