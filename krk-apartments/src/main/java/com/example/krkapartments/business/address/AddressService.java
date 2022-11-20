package com.example.krkapartments.business.address;

import com.example.krkapartments.domain.address.Address;
import com.example.krkapartments.domain.address.AddressMapper;
import com.example.krkapartments.endpoint.address.exception.AddressNotFoundException;
import com.example.krkapartments.exception.FieldDoesNotExistException;
import com.example.krkapartments.persistence.address.entity.AddressEntity;
import com.example.krkapartments.persistence.address.repository.AddressRepository;
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
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Transactional(readOnly = true)
    public List<Address> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::mapFromEntityToDomain)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<Address> save(Address address) {
        return Optional.ofNullable(address)
                .map(addressMapper::mapFromDomainToEntity)
                .map(addressRepository::save)
                .map(addressMapper::mapFromEntityToDomain);
    }

    @Transactional
    public Optional<Address> createAddress(Address address) {
        return this.save(address);
    }

    public Optional<Address> findById(UUID id) {
        return addressRepository.findById(id)
                .map(addressMapper::mapFromEntityToDomain);
    }

    public AddressEntity findAddressInDatabase(UUID id) {
        return addressRepository.findById(id)
                .orElseThrow(AddressNotFoundException::new);
    }

    public Optional<Address> updateAddress(UUID id, Map<Object, Object> fields) {
        AddressEntity addressEntity = findAddressInDatabase(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(AddressEntity.class, (String) key);
            if (field == null) {
                throw new FieldDoesNotExistException("Field " + key + " does not exist");
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, addressEntity, value);
        });
        return Optional.of(addressRepository.save(addressEntity))
                .map(addressMapper::mapFromEntityToDomain);
    }


}
