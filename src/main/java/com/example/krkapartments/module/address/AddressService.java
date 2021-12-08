package com.example.krkapartments.module.address;

import com.example.krkapartments.exception.AddressNotFoundException;
import com.example.krkapartments.exception.FieldDoesNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressDto> getAddressList() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(AddressConverter::convertToAddressDto)
                .collect(Collectors.toList());
    }

    public Address addAddress(AddressDto addressDto) {
        Address address = AddressConverter.convertDtoToAddress(addressDto);
        address.setId(UUID.randomUUID());
        Optional<Address> occurrences = addressRepository
                .findAllByCityAndStreetNameAndBuildingNumberAndApartmentNumber(address.getCity(),
                        address.getStreetName(), address.getBuildingNumber(), address.getApartmentNumber());
        if (occurrences.isEmpty()) {
            addressRepository.save(address);
            return address;
        }
        return occurrences.get();
    }

    public AddressDto findById(UUID id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException("Could not find address with id: " + id));
        return AddressConverter.convertToAddressDto(address);
    }

    public Address findAddressInDatabase(UUID id) {
        return addressRepository.findById(id).orElseThrow(() ->
                new AddressNotFoundException("Could not find address with id: " + id));
    }

    public AddressDto findByCity(String city) {
        Address address = addressRepository.findByCity(city).orElseThrow(() ->
                new AddressNotFoundException("Could not find city: " + city));
        return AddressConverter.convertToAddressDto(address);
    }

    public AddressDto updateAddress(UUID id, Map<Object, Object> fields) {
        Address address = findAddressInDatabase(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Address.class, (String) key);
            if (field == null) {
                throw new FieldDoesNotExistException("Field" + key + "does not exist");
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, address, value);
        });
        addressRepository.save(address);
        return AddressConverter.convertToAddressDto(address);
    }
}
