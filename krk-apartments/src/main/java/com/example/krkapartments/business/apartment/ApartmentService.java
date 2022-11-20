package com.example.krkapartments.business.apartment;

import com.example.krkapartments.endpoint.apartment.dto.ApartmentDto;
import com.example.krkapartments.endpoint.apartment.exception.ApartmentNotFoundException;
import com.example.krkapartments.exception.FieldDoesNotExistException;
import com.example.krkapartments.module.apartment.ApartmentConverter;
import com.example.krkapartments.persistence.apartment.entity.ApartmentEntity;
import com.example.krkapartments.persistence.apartment.repository.ApartmentRepository;
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

    public List<ApartmentDto> findAllActiveApartments() {
        List<ApartmentEntity> apartmentEntityList = apartmentRepository.findAllByActive(true);
        return apartmentEntityList.stream()
                .map(ApartmentConverter::convertApartmentToDto)
                .collect(Collectors.toList());
    }

    public ApartmentEntity addApartment(ApartmentDto apartmentDto) {
        ApartmentEntity apartmentEntity = ApartmentConverter.convertDtoToApartment(apartmentDto);
        apartmentEntity.setId(UUID.randomUUID());
        apartmentEntity.setActive(true);
        Optional<ApartmentEntity> occurrences = apartmentRepository
                .findByApartmentNameIgnoreCase(apartmentEntity.getApartmentName());
        if (occurrences.isEmpty()) {
            apartmentRepository.save(apartmentEntity);
            return apartmentEntity;
        }
        return occurrences.get();
    }

    public ApartmentEntity findApartmentInDatabase(UUID id) {
        return apartmentRepository.findById(id).orElseThrow(() ->
                new ApartmentNotFoundException("Could not find apartment with id: " + id));

    }

    public ApartmentDto findById(UUID id) {
        ApartmentEntity apartmentEntity = findApartmentInDatabase(id);
        return ApartmentConverter.convertApartmentToDto(apartmentEntity);
    }


    public ApartmentDto updateApartment(UUID id, Map<Object, Object> fields) {
        ApartmentEntity apartmentEntity = findApartmentInDatabase(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(ApartmentEntity.class, (String) key);
            if (field == null) {
                throw new FieldDoesNotExistException("Field " + key + " does not exist");
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, apartmentEntity, value);
        });
        apartmentRepository.save(apartmentEntity);
        return ApartmentConverter.convertApartmentToDto(apartmentEntity);
    }


    public ApartmentDto deactivateApartment(UUID id) {
        ApartmentEntity apartmentEntity = findApartmentInDatabase(id);
        apartmentEntity.setActive(false);
        apartmentRepository.save(apartmentEntity);
        return ApartmentConverter.convertApartmentToDto(apartmentEntity);
    }

    public List<ApartmentDto> findAll() {
        List<ApartmentEntity> apartmentEntities = apartmentRepository.findAll();
        return apartmentEntities.stream()
                .map(ApartmentConverter::convertApartmentToDto)
                .collect(Collectors.toList());
    }


}
