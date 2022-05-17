package com.springboot;

import com.springboot.userservice.services.FacilityService;
import com.springboot.userservice.services.UserService;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
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

    @Bean
    CommandLineRunner run(FacilityService facilityService, UserService userService) {
        return args -> {
            // Province province = new Province();
            // province.setName("Thanh Hoa");
            // facilityService.saveProvince(province);

            // District district = new District();
            // district.setName("Thanh Hoa City");
            // facilityService.saveDistrictByProvince(district, province);

            // Ward ward = new Ward();
            // ward.setName("Ngoc Trao");
            // facilityService.saveWardByDistrict(ward, district);

            // Address address = new Address();
            // address.setName("108 Ngoc Trao TP Thanh Hoa");
            // address = facilityService.saveAddress(address, ward);

            // userService.saveRole(new AppRole(null, "ROLE_ADMIN"));
            // userService.saveRole(new AppRole(null, "ROLE_USER"));
            // userService.saveRole(new AppRole(null, "ROLE_MANAGER"));

            // userService
            // .saveUser(new AppUser(null, "tt-tung261", "tung00deptrai",
            // "tt.tung261@gmail.com", "Thanh Tung",
            // null, null));

            // userService.addRoleToUser("tt.tung2612002", "ROLE_ADMIN");
            // userService.addRoleToUser("tt.tung2612002", "ROLE_USER");
            // userService.addAddressToUser(1, "tt-tung261");
        };
    }

}
