package com.example.Service;

import com.example.entity.Property;
import com.example.payload.PropertyDto;
import com.example.reposetry.PropertyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PropertyService {
private final PropertyRepository propertyRepository;
private final ModelMapper mapper;
@Transactional
public Property createReg(PropertyDto propertyDto){
  Property property = new Property();
  property.setName(propertyDto.getName());
  property.setCity(propertyDto.getCity());
  property.setNo_of_bedrooms(propertyDto.getNo_of_bedrooms());
  property.setNo_of_bathrooms(propertyDto.getNo_of_bathrooms());
  property.setNo_of_guest(propertyDto.getNo_of_guest());
  property.setNo_of_pets(propertyDto.getNo_of_pets());
  property.setCountry(propertyDto.getCountry());
  property.setCity(propertyDto.getCity());
    Property save = propertyRepository.save(property);
    return save;
}
    public List<PropertyDto>getAllData(){
      List<Property> all = propertyRepository.findAll();
      List<PropertyDto> list = all.stream().map(r -> mapToDto(r)).toList();
      return list;
    }

    public void deleteById(long id){
      Property property = propertyRepository.findById(id)
              .orElseThrow(()-> new NoSuchElementException(" id is Not Present"));
      propertyRepository.delete(property);
}

  public PropertyDto updateById (long id , PropertyDto propertyDto){
  Property property = propertyRepository.findById(id).orElseThrow(()->
          new NoSuchElementException("id is not present"));
    property.setName(propertyDto.getName());
    property.setCity(propertyDto.getCity());
    property.setNo_of_bedrooms(propertyDto.getNo_of_bedrooms());
    property.setNo_of_bathrooms(propertyDto.getNo_of_bathrooms());
    property.setNo_of_guest(propertyDto.getNo_of_guest());
    property.setNo_of_pets(propertyDto.getNo_of_pets());
    property.setCountry(propertyDto.getCountry());
    property.setCity(propertyDto.getCity());
    Property save = propertyRepository.save(property);
    return mapToDto(save);
  }
  public Property findById(long id){
    Property property = propertyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("id is not present"));
   return property;
  }

  public Property mapToEntity(PropertyDto propertyDto){
    Property map = mapper.map(propertyDto, Property.class);
    return map;
  }
  public PropertyDto mapToDto(Property property){
    PropertyDto map = mapper.map(property, PropertyDto.class);
    return map;
  }

  public List<Property>SearchHotels(String name){
    List<Property> properties = propertyRepository.searchHotels(name);
    return properties;
}


}
