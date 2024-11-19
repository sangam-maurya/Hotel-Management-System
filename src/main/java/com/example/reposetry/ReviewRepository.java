package com.example.reposetry;

import com.example.entity.AppUser;
import com.example.entity.Property;
import com.example.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

   List<Review> findByAppUser(AppUser user);

  boolean  existsByAppUserAndPropertyId(AppUser user, long property);

}