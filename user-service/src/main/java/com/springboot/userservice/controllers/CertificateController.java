package com.springboot.userservice.controllers;

import java.net.URI;

import com.springboot.userservice.dto.request.CertificateRequestDto;
import com.springboot.userservice.dto.response.BaseResponse;
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

    private final FacilityService facilityService;

    private final JwtTokenUtils jwtTokenUtils;

    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<BaseResponse> getAllCertificate(
            @RequestHeader(name = "Authorization") String userToken) {
        userToken = userToken.substring("Bearer ".length() + JwtTokenUtils.preToken.length());
        String username = jwtTokenUtils.getUsernameFromToken(userToken);
        BaseResponse response = new BaseResponse("0", "success",
                facilityService.getAllCertificateByUser(userService.getCurrentUser(username).getId()));
        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> addCertificateToFacility(
            @RequestBody CertificateRequestDto certificateDto) {
        // facilityService.addFacility(payload);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/certificates/create")
                        .toUriString());

        // certificate.setCertificateNumber(certificateDto.getCertificateNumber());

        // // // convert string to SQL date.
        // String publishedDateString = certificateDto.getPublishedDate();
        // Date publishedDate = java.sql.Date.valueOf(publishedDateString);
        // String expiredDateString = certificateDto.getExpiredDate();
        // Date expiredDate = java.sql.Date.valueOf(expiredDateString);
        int result = facilityService.saveCertificate(certificateDto);
        BaseResponse response = new BaseResponse(result == 1 ? "0" : "-1",
                result == 1 ? "Add certificate success" : "Add certificate failed", "");
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCertificate(@RequestBody CertificateRequestDto certificateDto) {
        // facilityService.addFacility(payload);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/certificates/delete")
                        .toUriString());

        // Facility facility =
        // facilityService.getFacilityById(certificateDto.getFacilityId());
        facilityService.deleteCertificateByNumber(certificateDto.getCertificateNumber());
        return ResponseEntity.created(uri).body("Certificate deleted successfully");
    }
}