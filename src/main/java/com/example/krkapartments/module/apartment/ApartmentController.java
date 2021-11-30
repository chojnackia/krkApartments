package com.example.krkapartments.module.apartment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/apartment")
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentService apartmentService;

    @GetMapping("/")
    public List<ApartmentDto> getApartments() {
        return apartmentService.getApartmentsList();
    }

    @PostMapping("/")
    public ApartmentDto addApartment(@Valid @RequestBody ApartmentDto apartmentDto) {
        return apartmentService.addApartment(apartmentDto);
    }


}
