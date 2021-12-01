package com.example.krkapartments.module.address;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/")
    public List<AddressDto> getAddresses() {
        return addressService.getAddressList();
    }

    @GetMapping("/{id}")
    public AddressDto getAddress(@PathVariable UUID id) {
        return addressService.findById(id);
    }

    @GetMapping("/{city}")
    public AddressDto getAddressByCity(@PathVariable String city){
        return addressService.findByCity(city);
    }

    @PostMapping("/")
    public AddressDto addLocation(@Valid @RequestBody AddressDto addressDto) {
        Address address = addressService.addAddress(addressDto);
        return AddressConverter.convertToAddressDto(address);
    }

    @PatchMapping("/{id}")
    public AddressDto updateAddress(@PathVariable UUID id, @RequestBody Map<Object, Object> fields){
        return addressService.updateAddress(id,fields);
    }
}
