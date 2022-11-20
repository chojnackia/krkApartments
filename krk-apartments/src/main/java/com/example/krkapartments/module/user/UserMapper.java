package com.example.krkapartments.module.user;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User mapFromEntityToDomain(UserEntity userEntity);

    UserEntity mapFromDomainToEntity(User user);

    UserDto mapFromDomainToDto(User user);

    User mapFromDtoToDomain(UserDto userDto);
}
