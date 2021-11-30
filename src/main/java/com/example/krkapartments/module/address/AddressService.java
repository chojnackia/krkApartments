package com.example.krkapartments.module.address;

import com.example.krkapartments.exception.AddressNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
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
                .findAllByCityAndStreetNameAndBuildingNumberAndRoomNumber(address.getCity(), address.getStreetName(), address.getBuildingNumber(), address.getRoomNumber());
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
}
