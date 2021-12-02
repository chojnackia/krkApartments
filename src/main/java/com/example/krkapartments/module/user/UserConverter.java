package com.example.krkapartments.module.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConverter {

    public static UserDto convertUserToDto(User user) {
        if (user == null) return null;
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();

    }

    public static User convertToUser(UserDto userDto) {
        if (userDto == null) return null;
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }

    public static List<User> convertDtoListToUserList(List<UserDto> userDtos) {
        return userDtos.stream()
                .map(UserConverter::convertToUser)
                .collect(Collectors.toList());
    }

    public static List<UserDto> convertUserListToDtoList(List<User> users) {
        return users.stream()
                .map(UserConverter::convertUserToDto)
                .collect(Collectors.toList());
    }

}
