package com.springboot.userservice.controllers;

import java.net.URI;
import java.sql.Date;
import java.util.List;

import com.springboot.userservice.dto.request.CertificateRequestDto;
import com.springboot.userservice.dto.response.CertificateResponseDto;
import com.springboot.userservice.entity.Certificate;
import com.springboot.userservice.entity.Facility;
import com.springboot.userservice.services.FacilityService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final FacilityService facilityService;

    @GetMapping("/list")
    public ResponseEntity<List<CertificateResponseDto>> getAllFacility() {
        return ResponseEntity.ok().body(facilityService.getAllCertificate());
    }

    @PostMapping("/create")
    public ResponseEntity<?> addCertificateToFacility(
            @RequestBody CertificateRequestDto certificateDto) {
        // facilityService.addFacility(payload);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/certificates/create")
                        .toUriString());

        Certificate certificate = new Certificate();
        certificate.setCertificateNumber(certificateDto.getCertificateNumber());

        // convert string to SQL date.
        String publishedDateString = certificateDto.getPublishedDate();
        Date publishedDate = java.sql.Date.valueOf(publishedDateString);
        String expiredDateString = certificateDto.getExpiredDate();
        Date expiredDate = java.sql.Date.valueOf(expiredDateString);
        certificate.setPublishedDate(publishedDate);
        certificate.setExpiredDate(expiredDate);

        // map state of certificateDto to certificate
        certificate
                .setCertificateState(facilityService.getCertificateStateByName(certificateDto.getCertificateState()));

        Facility facility = facilityService.getFacilityById(certificateDto.getFacilityId());
        certificate.setFacility(facility);

        facilityService.saveCertificate(certificate);

        return ResponseEntity.created(uri).body("Certificate created successfully");
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