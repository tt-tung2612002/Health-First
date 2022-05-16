package com.springboot.userservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/facilities")
@RequiredArgsConstructor
public class FacilityController {

    // @GetMapping("/list")
    // public ResponseEntity<List<Facility>> getUsers() {
    //     return ResponseEntity.ok().body(userService.getUsers());
    // }

}