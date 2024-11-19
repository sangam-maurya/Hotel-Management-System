package com.example.reposetry;

import com.example.entity.Images;
import com.example.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImagesRepository extends JpaRepository<Images, Long> {
    List<Images> findByProperty(Property property);
}