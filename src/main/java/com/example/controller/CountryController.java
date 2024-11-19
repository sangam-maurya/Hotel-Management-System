package com.example.controller;

import com.example.entity.AppUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/country")
public class CountryController {

    @PostMapping("/add-country")
    public AppUser addCountry(
            @AuthenticationPrincipal AppUser user
            ){
       return user;
    }
    @PostMapping("/name")
    public String name(){
        return "raj";
    }
}
