package com.example.krkapartments.module.admin;

import java.util.List;
import java.util.stream.Collectors;

public class AdminConverter {

    public static Admin convertToAdmin(AdminDto adminDto) {
        return Admin.builder()
                .id(adminDto.getId())
                .firstName(adminDto.getFirstName())
                .lastName(adminDto.getLastName())
                .email(adminDto.getEmail())
                .role(adminDto.getRole())
                .password(adminDto.getPassword())
                .active(adminDto.isActive())
                .build();
    }

    public static AdminDto convertToAdminDto(Admin admin) {
        return AdminDto.builder()
                .id(admin.getId())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .email(admin.getEmail())
                .role(admin.getRole())
                .active(admin.isActive())
                .build();
    }

    public static List<AdminDto> entityToDto(List<Admin> admins) {
        return admins.stream()
                .map(AdminConverter::convertToAdminDto)
                .collect(Collectors.toList());
    }

}
