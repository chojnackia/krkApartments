package com.example.krkapartments.module.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConverter {

    public static UserDto convertUserToDto(UserEntity userEntity) {
        if (userEntity == null) return null;
        return UserDto.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .telephoneNumber(userEntity.getTelephoneNumber())
                .build();

    }

    public static UserEntity convertToUser(UserDto userDto) {
        if (userDto == null) return null;
        return UserEntity.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .telephoneNumber(userDto.getTelephoneNumber())
                .build();
    }

    public static List<UserEntity> convertDtoListToUserList(List<UserDto> userDtos) {
        return userDtos.stream()
                .map(UserConverter::convertToUser)
                .collect(Collectors.toList());
    }

    public static List<UserDto> convertUserListToDtoList(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(UserConverter::convertUserToDto)
                .collect(Collectors.toList());
    }

}
