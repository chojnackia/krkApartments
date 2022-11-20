package com.example.krkapartments.module.admin;

import org.mapstruct.Mapper;

@Mapper
public interface AdminMapper {
    Admin mapFromEntityToDomain(AdminEntity adminEntity);

    AdminEntity mapFromDomainToEntity(Admin admin);

    AdminDto mapFromDomainToDto(Admin admin);

    Admin mapFromDtoToDomain(AdminDto adminDto);
}
