package com.example.Service;

import com.example.entity.AppUser;
import com.example.payload.LoginDto;
import com.example.reposetry.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final AppUserRepository repository;
    private final JWTService jwtService;

    public String verifyLogin(LoginDto loginDto) {
//        System.out.println("Attempting to login with username: " + loginDto.getUsername());

        Optional<AppUser> opUser = repository.findByUsername(loginDto.getUsername());
        if (opUser.isPresent()) {
            AppUser appUser = opUser.get();
//            System.out.println("User found: " + appUser.getUsername());
//            System.out.println("Stored Password Hash: " + appUser.getPassword());
//            System.out.println("Provided Password: " + loginDto.getPassword());

            if (BCrypt.checkpw(loginDto.getPassword(), appUser.getPassword())) {
                String token = jwtService.generateToken(loginDto.getUsername());
//                System.out.println("Generated token: " + token);
                return token;
            } else {
                System.out.println("Password mismatch for user: " + appUser.getUsername());
            }
        } else {
            System.out.println("User not found: " + loginDto.getUsername());
        }

        return null;
    }


//    public List<AppUser>  getAll (){
//        List<AppUser> all = repository.findAll();
//        return all;
//    }

}
