package com.example.krkapartments.generator;

import com.example.krkapartments.persistence.user.entity.UserEntity;
import com.example.krkapartments.util.DateTimeProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserGenerator {

    private static final UUID id1 = UUID.randomUUID();
    private static final UUID id2 = UUID.randomUUID();
    private static final LocalDate now = DateTimeProvider.getLocalDateToday();

    static List<UserEntity> generateUserList() {
        List<UserEntity> userEntities = new ArrayList<>();

        UserEntity userEntity1 = UserEntity.builder()
                .id(id1)
                .firstName("Adam")
                .lastName("Chojnacki")
                .email("adam.chojnacki@gft.com")
                .telephoneNumber("987654321")
                .build();

        UserEntity userEntity2 = UserEntity.builder()
                .id(id2)
                .firstName("Eryk")
                .lastName("Mikuła")
                .email("eryk.mikula@gft.com")
                .telephoneNumber("123456789")
                .build();

        userEntities.add(userEntity1);
        userEntities.add(userEntity2);

        return userEntities;
    }

    static List<com.example.krkapartments.endpoint.user.dto.UserDto> generateUserDtoList() {
        List<com.example.krkapartments.endpoint.user.dto.UserDto> userDtos = new ArrayList<>();

        com.example.krkapartments.endpoint.user.dto.UserDto userDto1 = com.example.krkapartments.endpoint.user.dto.UserDto.builder()
                .id(id1)
                .firstName("Adam")
                .lastName("Chojnacki")
                .email("adam.chojnacki@gft.com")
                .telephoneNumber("987654321")
                .build();

        com.example.krkapartments.endpoint.user.dto.UserDto userDto2 = com.example.krkapartments.endpoint.user.dto.UserDto.builder()
                .id(id2)
                .firstName("Eryk")
                .lastName("Mikuła")
                .email("eryk.mikula@gft.com")
                .telephoneNumber("123456789")
                .build();

        userDtos.add(userDto1);
        userDtos.add(userDto2);

        return userDtos;
    }
}
