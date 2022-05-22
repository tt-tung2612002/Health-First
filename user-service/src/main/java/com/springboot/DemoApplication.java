package com.springboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
// @RequiredArgsConstructor
public class DemoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return configureApplication(builder);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(applicationClass).bannerMode(Banner.Mode.OFF);
    }

    public static void main(String[] args) {
        SpringApplication.run(applicationClass, args);
    }

    private static Class<DemoApplication> applicationClass = DemoApplication.class;

    // @Bean
    // CommandLineRunner run(FacilityService facilityService, UserService
    // userService,
    // StaticDataService staticDataService) {
    // return args -> {

    // // // add a new user named duytuan28

    // // // List<DistrictResponseDto> list =
    // // staticDataService.getDistrictsByProvince(1);
    // // // userService.saveRole(new AppRole(null, "ROLE_SUPER_ADMIN"));
    // // // userService.saveRole(new AppRole(null, "ROLE_ADMIN"));
    // // // userService.saveRole(new AppRole(null, "ROLE_USER"));
    // // // userService.saveRole(new AppRole(null, "ROLE_MANAGER"));

    // // // userService
    // // // .saveUser(new AppUser(null, "tt-tung261", "tung00deptrai",
    // // // "tt.tung261@gmail.com", "Thanh Tung",
    // // // new HashSet<>(), null, null));

    // // // userService.addRoleToUser("tt-tung261", "ROLE_ADMIN");

    // // Address address = new Address();
    // // address.setName("108 Ngoc Trao TP Thanh Hoa");
    // // facilityService.saveAddress(address, facilityService.getWardById(1));

    // // Facility facility = new Facility();
    // // facility.setFacilityCode("FAC-001");
    // // facility.setAddress(address);
    // // facility.setName("Highlands");

    // //
    // facility.setFacilityState(facilityService.getFacilityStateByName("active"));
    // // facility.setBusinessType(facilityService.getBusinessTypeById(1));

    // Certificate certificate = new Certificate();
    // certificate.setCertificateNumber("CERT-001");
    // certificate.setFacility(facilityService.findFacilityById(9));
    // certificate.setPublishedDate(new java.sql.Date(System.currentTimeMillis()));

    // // // set expired date equal to published date + 1 year.
    // certificate.setExpiredDate(new java.sql.Date(System.currentTimeMillis() +
    // (1000 * 60 * 60 * 24 * 365)));

    // certificate.setCertificateState(facilityService.getCertificateStateByName("active"));
    // facilityService.saveCertificate(certificate);

    // // facility.getCertificates().add(certificate);
    // // facilityService.saveFacility(facility);

    // };
    // }

}
