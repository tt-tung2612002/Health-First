package com.springboot.userservice.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.userservice.dto.response.BaseResponse;
import com.springboot.userservice.entity.District;
import com.springboot.userservice.entity.Province;
import com.springboot.userservice.services.StaticDataService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/static/data")
@RequiredArgsConstructor
@Threads(1)
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class StaticDataController {

    private final StaticDataService staticDataService;

    public List<?> benchmarkMethod1() {
        return staticDataService.testBenchmarkMethod1();
    }

    public List<?> benchmarkMethod2() {
        return staticDataService.testBenchmarkMethod1();
    }

    @PostMapping("/provinces")
    public ResponseEntity<?> getProvinces() {
        return ResponseEntity.ok().body(new BaseResponse("1", "Get all provinces successfully",
                staticDataService.getProvinces()));
    }

    @PostMapping("/districts")
    public ResponseEntity<?> getDistrictsByProvince(@RequestBody Province province) {

        // get districts using querydsl.
        // JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        // QDistrict qDistrict = QDistrict.district;
        // var districts =
        // queryFactory.selectFrom(qDistrict).where(qDistrict.province.eq(province)).limit(50);
        // System.out.println(districts.fetch());

        return ResponseEntity.ok().body(new BaseResponse("1", "Get all districts successfully",
                staticDataService.getDistrictsByProvince(province.getId())));
    }

    @PostMapping("/wards")
    public ResponseEntity<?> getWardsByDistrict(@RequestBody District district) {
        return ResponseEntity.ok().body(new BaseResponse("1", "Get all wards successfully",
                staticDataService.getWardsByDistrict(district.getId())));
    }

    @GetMapping("/foods")
    public ResponseEntity<?> getFoods() {
        return ResponseEntity.ok().body(new BaseResponse("1", "Get all foods successfully",
                staticDataService.getFoods()));
    }

    @GetMapping("/inspection-units")
    public ResponseEntity<?> getInspectionUnits() {
        return ResponseEntity.ok().body(new BaseResponse("1", "Get all inspection units successfully",
                staticDataService.getInspectionUnits()));
    }
}