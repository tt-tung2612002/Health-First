package com.springboot.userservice;

import java.util.HashSet;

import com.springboot.userservice.entity.AppRole;
import com.springboot.userservice.entity.AppUser;
import com.springboot.userservice.services.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new AppRole(null, "ROLE_SUPER_ADMIN"));
            userService.saveRole(new AppRole(null, "ROLE_ADMIN"));
            userService.saveRole(new AppRole(null, "ROLE_USER"));
            userService.saveRole(new AppRole(null, "ROLE_MANAGER"));

            userService
                    .saveUser(new AppUser(null, "tt.tung261", "tung00deptrai", "tt.tung261@gmail.com", "Thanh Tung",
                            new HashSet<>()));
           


            userService.addRoleToUser("tt.tung261", "ROLE_ADMIN");
        };
    }
    // @CrossOrigin(origins = "http://localhost:8080")
    // @RequestMapping("/")
    // public String index() {
    // return "Greetings from Spring Boot!";
    // }

    // @Bean
    // @LoadBalanced
    // public RestTemplate restTemplate() {
    // return new RestTemplate();
    // }

}
