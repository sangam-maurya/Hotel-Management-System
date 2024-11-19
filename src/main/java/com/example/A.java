package com.example;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class A {
    public static void main(String[] args) {
        boolean checkpw = BCrypt.checkpw("sangam", "$2a$05$H2dXH2WIC.Qzl26/v2.ylORKMg55lz2QXryeweguzeeTIFUxeOLQ2");
        System.out.println(checkpw);


        System.out.println( " ");
        String hashpw = BCrypt.hashpw("sangam", BCrypt.gensalt(5));
        System.out.println(hashpw);
    }


}
