package com.example.krkapartments.module.apartment;

import com.example.krkapartments.module.address.Address;
import com.example.krkapartments.module.address.AddressConverter;
import com.example.krkapartments.module.address.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/apartment")
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentService apartmentService;

    @GetMapping("/")
    public List<ApartmentDto> getApartments() {
        return apartmentService.getApartmentsList();
    }

    @GetMapping("/{id}")
    public ApartmentDto getApartment(@PathVariable UUID id){
        return apartmentService.findById(id);
    }

    @PostMapping("/")
    public ApartmentDto addLocation(@Valid @RequestBody ApartmentDto apartmentDto) {
        Apartment apartment = apartmentService.addApartment(apartmentDto);
        return ApartmentConverter.convertApartmentToDto(apartment);
    }

    @PatchMapping("/{id}")
    public ApartmentDto updateApartment(@PathVariable UUID id, @RequestBody Map<Object, Object> fields){
        return apartmentService.updateApartment(id, fields);
    }


}
