package com.springboot.userservice.dto.request;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchFilterRequest {

    private Integer userId;
    private String searchText;
    private Integer provinceId;
    private Integer districtId;
    private Integer wardId;
    private Integer businessTypeId;
    private Integer facilityStateId;
    private String sortOrder;
    private Integer pageNumber;
    private Integer pageSize;

    public static void main(String[] args) {
        SearchFilterRequest searchFilterRequest = new SearchFilterRequest();
        System.out.println(new Gson().toJson(searchFilterRequest));
    }

}