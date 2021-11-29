package com.example.krkapartments.module.address;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/")
    public List<AddressDto> getAllAddresses() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public AddressDto getAddress(@PathVariable UUID id) {
        return addressService.findById(id);
    }

    @PostMapping("/")
    public AddressDto addLocation(@Valid @RequestBody AddressDto addressDto) {
        Address address = addressService.addAddress(addressDto);
        return AddressConverter.convertToAddressDto(address);
    }
}
