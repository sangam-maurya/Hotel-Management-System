package com.example.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    private String username;

    private  String password;

    private String phoneNumber;
    private String otp;

}
