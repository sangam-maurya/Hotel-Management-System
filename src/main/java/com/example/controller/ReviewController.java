package com.example.controller;

import com.example.entity.AppUser;
import com.example.entity.Property;
import com.example.entity.Review;
import com.example.reposetry.PropertyRepository;
import com.example.reposetry.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final PropertyRepository propertyRepository;
    private final ReviewRepository reviewRepository;

    @PostMapping
    public ResponseEntity<?>write(
            @RequestBody  Review review ,
            @RequestParam long propertyId ,
            @AuthenticationPrincipal AppUser appUser
            ){
        Property property = propertyRepository.findById(propertyId).get();
        if (reviewRepository.existsByAppUserAndPropertyId(appUser, propertyId)){
            return new ResponseEntity<>("You have already reviewed this property", HttpStatus.BAD_REQUEST);
        }
        review.setProperty(property);
        review.setAppUser(appUser);
        Review save = reviewRepository.save(review);
        return new ResponseEntity<>(save , HttpStatus.OK);
    }
    @GetMapping("/user/review")
    public ResponseEntity<List<Review>>getUserReview(
            @AuthenticationPrincipal AppUser user
    ){
        List<Review> reviews = reviewRepository.findByAppUser(user);
        return new ResponseEntity<>(reviews , HttpStatus.OK);
    }
}
