package com.springboot;

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
    CommandLineRunner run() {
        return args -> {
            // create district, province, ward, address
            
        };
    }

}
