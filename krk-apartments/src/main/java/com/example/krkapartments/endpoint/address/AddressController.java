package com.example.krkapartments.endpoint.address;

import com.example.krkapartments.business.address.AddressService;
import com.example.krkapartments.domain.address.AddressMapper;
import com.example.krkapartments.endpoint.address.dto.AddressCreateCommand;
import com.example.krkapartments.endpoint.address.dto.AddressDto;
import com.example.krkapartments.endpoint.address.exception.AddressCreationException;
import com.example.krkapartments.endpoint.address.exception.AddressNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @GetMapping
    public List<AddressDto> getAddresses() {
        return addressService.findAll()
                .stream()
                .map(addressMapper::mapFromDomainToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AddressDto getAddress(@PathVariable UUID id) {
        return addressService.findById(id)
                .map(addressMapper::mapFromDomainToDto)
                .orElseThrow(AddressNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDto addLocation(@Valid @RequestBody AddressCreateCommand command) {
        return Optional.ofNullable(command)
                .map(addressMapper::mapFromCreateCommandToDomain)
                .flatMap(addressService::createAddress)
                .map(addressMapper::mapFromDomainToDto)
                .orElseThrow(AddressCreationException::new);
    }

    @PatchMapping("/{id}")
    public AddressDto updateAddress(@PathVariable UUID id, @RequestBody Map<Object, Object> fields) {
        return addressService.updateAddress(id, fields)
                .map(addressMapper::mapFromDomainToDto)
                .orElseThrow(AddressNotFoundException::new);
    }
}
