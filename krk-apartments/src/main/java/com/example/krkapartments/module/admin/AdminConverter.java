package com.example.krkapartments.module.admin;

import java.util.List;
import java.util.stream.Collectors;

public class AdminConverter {

    public static AdminEntity convertToAdmin(AdminDto adminDto) {
        return AdminEntity.builder()
                .id(adminDto.getId())
                .firstName(adminDto.getFirstName())
                .lastName(adminDto.getLastName())
                .email(adminDto.getEmail())
                .role(adminDto.getRole())
                .password(adminDto.getPassword())
                .active(adminDto.isActive())
                .build();
    }

    public static AdminDto convertToAdminDto(AdminEntity adminEntity) {
        return AdminDto.builder()
                .id(adminEntity.getId())
                .firstName(adminEntity.getFirstName())
                .lastName(adminEntity.getLastName())
                .email(adminEntity.getEmail())
                .role(adminEntity.getRole())
                .active(adminEntity.isActive())
                .build();
    }

    public static List<AdminDto> entityToDto(List<AdminEntity> adminEntities) {
        return adminEntities.stream()
                .map(AdminConverter::convertToAdminDto)
                .collect(Collectors.toList());
    }

}
