package com.example.krkapartments.endpoint.apartment;

import com.example.krkapartments.business.apartment.ApartmentService;
import com.example.krkapartments.domain.apartment.ApartmentMapper;
import com.example.krkapartments.endpoint.apartment.dto.ApartmentCreateCommand;
import com.example.krkapartments.endpoint.apartment.dto.ApartmentDto;
import com.example.krkapartments.endpoint.apartment.exception.ApartmentCreationException;
import com.example.krkapartments.endpoint.apartment.exception.ApartmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/apartments")
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentService apartmentService;
    private final ApartmentMapper apartmentMapper;

    @GetMapping
    public List<ApartmentDto> getApartmentsList() {
        return apartmentService.findAllActiveApartments()
                .stream()
                .map(apartmentMapper::mapFromDomainToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ApartmentDto getApartment(@PathVariable UUID id) {
        return Optional.ofNullable(id)
                .flatMap(apartmentService::findById)
                .map(apartmentMapper::mapFromDomainToDto)
                .orElseThrow(ApartmentNotFoundException::new);
    }

    @PostMapping
    public ApartmentDto addApartment(@Valid @RequestBody ApartmentCreateCommand apartmentCreateCommand) {
        return Optional.ofNullable(apartmentCreateCommand)
                .map(apartmentMapper::mapFromCreateCommandToDomain)
                .flatMap(apartmentService::createApartment)
                .map(apartmentMapper::mapFromDomainToDto)
                .orElseThrow(ApartmentCreationException::new);
    }

    @PatchMapping("/{id}")
    public ApartmentDto updateApartment(@PathVariable UUID id, @RequestBody Map<Object, Object> fields) {
        return Optional.ofNullable(id)
                .flatMap(uuid -> apartmentService.updateApartment(uuid, fields))
                .map(apartmentMapper::mapFromDomainToDto)
                .orElseThrow(ApartmentNotFoundException::new);
    }

    @PatchMapping("/deactivate/{id}")
    public ApartmentDto deactivateApartment(@PathVariable UUID id) {
        return Optional.ofNullable(id)
                .flatMap(apartmentService::deactivateApartment)
                .map(apartmentMapper::mapFromDomainToDto)
                .orElseThrow(ApartmentNotFoundException::new);
    }
}
