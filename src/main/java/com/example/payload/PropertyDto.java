package com.example.payload;

import com.example.entity.City;
import com.example.entity.Country;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDto {
    private Long id;
    private String name;
    private Integer no_of_guest;
    private Integer no_of_bedrooms;
    private Integer no_of_bathrooms;
    private Integer no_of_pets;
    private Country country;
    private City city;
}
