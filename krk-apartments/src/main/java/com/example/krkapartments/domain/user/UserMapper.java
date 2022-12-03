package com.example.krkapartments.domain.user;

import com.example.krkapartments.endpoint.user.dto.UserCreateCommand;
import com.example.krkapartments.endpoint.user.dto.UserDto;
import com.example.krkapartments.persistence.user.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User mapFromEntityToDomain(UserEntity userEntity);

    UserEntity mapFromDomainToEntity(User user);

    UserDto mapFromDomainToDto(User user);

    User mapFromDtoToDomain(UserDto userDto);

    User mapFromCreateCommandToDomain(UserCreateCommand command);
}
