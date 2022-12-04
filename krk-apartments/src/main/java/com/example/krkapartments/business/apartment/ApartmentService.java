package com.example.krkapartments.business.apartment;

import com.example.krkapartments.domain.apartment.Apartment;
import com.example.krkapartments.domain.apartment.ApartmentMapper;
import com.example.krkapartments.exception.FieldDoesNotExistException;
import com.example.krkapartments.persistence.apartment.entity.ApartmentEntity;
import com.example.krkapartments.persistence.apartment.repository.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;

    @Transactional
    public Optional<Apartment> save(Apartment apartment) {
        return Optional.ofNullable(apartment)
                .map(apartmentMapper::mapFromDomainToEntity)
                .map(apartmentRepository::save)
                .map(apartmentMapper::mapFromEntityToDomain);
    }

    @Transactional(readOnly = true)
    public List<Apartment> findAllActiveApartments() {
        return apartmentRepository.findAllByActive(true)
                .stream()
                .map(apartmentMapper::mapFromEntityToDomain)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<Apartment> createApartment(Apartment apartment) {
        return this.save(apartment);
    }

    @Transactional(readOnly = true)
    public Optional<Apartment> findById(UUID id) {
        return apartmentRepository.findById(id)
                .map(apartmentMapper::mapFromEntityToDomain);
    }

    @Transactional
    public Optional<Apartment> updateApartment(UUID id, Map<Object, Object> fields) {
        Optional<Apartment> apartment = findById(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(ApartmentEntity.class, (String) key);
            if (field == null) {
                throw new FieldDoesNotExistException("Field " + key + " does not exist");
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, apartment, value);
        });
        return apartment
                .map(apartmentMapper::mapFromDomainToEntity)
                .map(apartmentRepository::save)
                .map(apartmentMapper::mapFromEntityToDomain);
    }

    @Transactional(readOnly = true)
    public Optional<Apartment> deactivateApartment(UUID id) {
        return findById(id)
                .map(apartment -> apartment.toBuilder()
                        .active(false)
                        .build())
                .map(apartmentMapper::mapFromDomainToEntity)
                .map(apartmentRepository::save)
                .map(apartmentMapper::mapFromEntityToDomain);
    }

    @Transactional(readOnly = true)
    public List<Apartment> findAll() {
        return apartmentRepository.findAll()
                .stream()
                .map(apartmentMapper::mapFromEntityToDomain)
                .collect(Collectors.toList());
    }
}
