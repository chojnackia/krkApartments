package com.example.krkapartments.module.apartment;

import com.example.krkapartments.exception.ApartmentNotFoundException;
import com.example.krkapartments.exception.FieldDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    public List<ApartmentDto> getApartmentsList() {
        return apartmentRepository.findAll()
                .stream()
                .map(ApartmentConverter::convertApartmentToDto)
                .collect(Collectors.toList());
    }

    public Apartment addApartment(ApartmentDto apartmentDto) {
        Apartment apartment = ApartmentConverter.convertDtoToApartment(apartmentDto);
        apartment.setId(UUID.randomUUID());
        Optional<Apartment> occurrences = apartmentRepository
                .findByApartmentNameIgnoreCase(apartment.getApartmentName());
        if (occurrences.isEmpty()){
            apartmentRepository.save(apartment);
            return apartment;
        }
        return occurrences.get();
    }

    public Apartment findApartmentInDatabase(UUID id){
        return apartmentRepository.findById(id).orElseThrow(()->
                new ApartmentNotFoundException("Could not find Apartment with id: " + id));

    }

    public Apartment findApartmentInDatabase(String name){
        return apartmentRepository.findByApartmentNameIgnoreCase(name).orElseThrow(()->
                new ApartmentNotFoundException("Could not find Apartment with name: " + name));

    }



    public ApartmentDto updateApartment(UUID id, Map<Object, Object> fields){
        Apartment apartment = findApartmentInDatabase(id);
        fields.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(Apartment.class, (String)  key);
            if(field==null){
                throw new FieldDoesNotExistException("Field" + key + "does not exist");
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, apartment, value);
        });
        apartmentRepository.save(apartment);
        return ApartmentConverter.convertApartmentToDto(apartment);
    }

    public ApartmentDto findById(UUID id) {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(()-> new ApartmentNotFoundException("Could not find apartment with id: " + id));
        return ApartmentConverter.convertApartmentToDto(apartment);
    }
}
