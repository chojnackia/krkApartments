package com.example.krkapartments.module.apartment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/apartments")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentService apartmentService;

    @GetMapping
    public List<ApartmentDto> getApartmentsList() {
        return apartmentService.findAllActiveApartments();
    }

    @GetMapping("/{id}")
    public ApartmentDto getApartment(@PathVariable UUID id) {
        return apartmentService.findById(id);
    }

    @PostMapping
    public ApartmentDto addApartment(@Valid @RequestBody ApartmentDto apartmentDto) {
        ApartmentEntity apartmentEntity = apartmentService.addApartment(apartmentDto);
        return ApartmentConverter.convertApartmentToDto(apartmentEntity);
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
