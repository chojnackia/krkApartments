package com.example.krkapartments.domain.admin;

import com.example.krkapartments.endpoint.admin.dto.AdminDto;
import com.example.krkapartments.persistence.admin.entity.AdminEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AdminMapper {
    Admin mapFromEntityToDomain(AdminEntity adminEntity);

    AdminEntity mapFromDomainToEntity(Admin admin);

    AdminDto mapFromDomainToDto(Admin admin);

    Admin mapFromDtoToDomain(AdminDto adminDto);

    AdminDto mapFromEntityToDto(AdminEntity adminEntity);
}
