package com.example.controller;

import com.example.Service.UserService;
import com.example.entity.AppUser;
import com.example.payload.LoginDto;
import com.example.payload.TokenDto;
import com.example.reposetry.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final AppUserRepository repository;
    private final UserService  service;

    @PostMapping("/signup")
    public ResponseEntity<?>createUser(
            @RequestBody AppUser user
    ){
        Optional<AppUser> opUserName = repository.findByUsername(user.getUsername());
        if (opUserName.isPresent()){
            return new ResponseEntity<>("Username alredy taken" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> opEmail = repository.findByEmail(user.getEmail());
        if (opEmail.isPresent()){
            return new ResponseEntity<>("Email already taken" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String hashpw = BCrypt.hashpw(user.getUsername(), BCrypt.gensalt(5));
        System.out.println(hashpw);
        user.setPassword(hashpw);
        user.setRole("ROLE_USER");
        AppUser saveUser = repository.save(user);
        return new ResponseEntity<>(saveUser , HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?>login ( @RequestBody LoginDto dto){
        String token = service.verifyLogin(dto);
        if (token!=null){
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setType("JWT");
            return new ResponseEntity<>(tokenDto , HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid Password" , HttpStatus.FORBIDDEN);
        }
    }

//    @GetMapping("/getData")
//    public ResponseEntity<List<AppUser>>findAllData(){
//        List<AppUser> all = service.getAll();
//        return new ResponseEntity<>(all , HttpStatus.OK);
//    }

    @PostMapping("/admin")
    public ResponseEntity<?> createAdmin(
            @RequestBody AppUser appUser
    ){

        Optional<AppUser> opUserName = repository.findByUsername(appUser.getUsername());

        if (opUserName.isPresent()){
            return new ResponseEntity<>("Username alredy taken" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<AppUser> opEmail = repository.findByEmail(appUser.getEmail());

        if (opEmail.isPresent()){
            return new ResponseEntity<>("Email already taken" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String hashpw = BCrypt.hashpw(appUser.getUsername(), BCrypt.gensalt(5));
        System.out.println(hashpw);
        appUser.setPassword(hashpw);
        appUser.setRole("ROLE_ADMIN");
        AppUser saveUser = repository.save(appUser);
        return new ResponseEntity<>(saveUser , HttpStatus.CREATED);
    }
}


