package com.example.controller;
import com.example.Service.BucketService;
import com.example.Service.PropertyService;
import com.example.entity.AppUser;
import com.example.entity.Images;
import com.example.entity.Property;
import com.example.payload.PropertyDto;
import com.example.reposetry.ImagesRepository;
import com.example.reposetry.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
   private BucketService bucketService;
   private PropertyService propertyService;
   private ImagesRepository imagesRepository;
    private final PropertyRepository propertyRepository;

    public ImageController(BucketService bucketService, PropertyService propertyService, ImagesRepository imagesRepository,
                           PropertyRepository propertyRepository) {
        this.bucketService = bucketService;
        this.propertyService = propertyService;
        this.imagesRepository = imagesRepository;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping(path = "/upload/file/{bucketName}/property/{propertyId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadPropertyPhotos(@RequestParam MultipartFile file,
                                        @PathVariable String bucketName,
                                        @PathVariable long propertyId,
                                        @AuthenticationPrincipal AppUser user
    ) {
        Property property = propertyService.findById(propertyId);
        String imageUrl = bucketService.uploadFile(file, bucketName);
        Images images = new Images();
        images.setUrl(imageUrl);
        images.setProperty(property);
        Images save = imagesRepository.save(images);
        System.out.println(save);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<Images>>getAllImages(
            @PathVariable long propertyId
    ){
        Property property = propertyRepository.findById(propertyId).get();
        List<Images> property1 = imagesRepository.findByProperty(property);
        return new ResponseEntity<>(property1 , HttpStatus.OK);
    }
}
