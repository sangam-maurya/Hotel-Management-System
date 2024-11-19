package com.example.controller;

import com.example.Service.PropertyService;
import com.example.entity.Property;
import com.example.payload.PropertyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2")
public class PropertyController {
private final PropertyService propertyService;

@PostMapping("/property")
public Property createReg(@RequestBody PropertyDto propertyDto){
    Property reg = propertyService.createReg(propertyDto);
    return reg;
}

@GetMapping
public List<PropertyDto>getAllData(){
    List<PropertyDto> allData = propertyService.getAllData();
    return allData;
}

@DeleteMapping("/{id}")
public String deleteById(@PathVariable long id){
     propertyService.deleteById(id);
    return  "deleted";
}
    @PutMapping("/{id}")
    public PropertyDto updateById(@PathVariable long id , @RequestBody PropertyDto propertyDto){
        PropertyDto propertyDto1 = propertyService.updateById(id, propertyDto);
        return propertyDto1;
    }
    @GetMapping("/search-hotels")
    public List<Property>searchHotels(@RequestParam String name){
        List<Property> properties = propertyService.SearchHotels(name);
        return properties;
    }
}
