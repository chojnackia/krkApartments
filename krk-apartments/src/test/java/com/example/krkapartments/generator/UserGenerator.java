package com.example.krkapartments.generator;

import com.example.krkapartments.module.user.User;
import com.example.krkapartments.module.user.UserDto;
import com.example.krkapartments.util.DateTimeProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserGenerator {

    private static final UUID id1 = UUID.randomUUID();
    private static final UUID id2 = UUID.randomUUID();
    private static final LocalDate now = DateTimeProvider.getLocalDateToday();

    static List<User> generateUserList() {
        List<User> users = new ArrayList<>();

        User user1 = User.builder()
                .id(id1)
                .firstName("Adam")
                .lastName("Chojnacki")
                .email("adam.chojnacki@gft.com")
                .telephoneNumber("987654321")
                .build();

        User user2 = User.builder()
                .id(id2)
                .firstName("Eryk")
                .lastName("Mikuła")
                .email("eryk.mikula@gft.com")
                .telephoneNumber("123456789")
                .build();

        users.add(user1);
        users.add(user2);

        return users;
    }

    static List<UserDto> generateUserDtoList() {
        List<UserDto> userDtos = new ArrayList<>();

        UserDto userDto1 = UserDto.builder()
                .id(id1)
                .firstName("Adam")
                .lastName("Chojnacki")
                .email("adam.chojnacki@gft.com")
                .telephoneNumber("987654321")
                .build();

        UserDto userDto2 = UserDto.builder()
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
