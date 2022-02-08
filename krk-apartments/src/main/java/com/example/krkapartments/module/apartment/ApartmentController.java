package com.example.krkapartments.module.apartment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/apartments")
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentService apartmentService;

    @GetMapping("/")
    public List<ApartmentDto> getApartmentsList() {
        return apartmentService.findAllActiveApartments();
    }

    @GetMapping("/{id}")
    public ApartmentDto getApartment(@PathVariable UUID id) {
        return apartmentService.findById(id);
    }

    @PostMapping("/")
    public ApartmentDto addApartment(@Valid @RequestBody ApartmentDto apartmentDto) {
        Apartment apartment = apartmentService.addApartment(apartmentDto);
        return ApartmentConverter.convertApartmentToDto(apartment);
    }

    @PatchMapping("/{id}")
    public ApartmentDto updateApartment(@PathVariable UUID id, @RequestBody Map<Object, Object> fields) {
        return apartmentService.updateApartment(id, fields);
    }

    @PatchMapping("/deactivate/{id}")
    public ApartmentDto deactivateApartment(@PathVariable UUID id) {
        return apartmentService.deactivateApartment(id);
    }


}
