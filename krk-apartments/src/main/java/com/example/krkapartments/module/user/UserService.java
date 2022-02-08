package com.example.krkapartments.module.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;
    UserConverter userConverter;

    public User addUser(UserDto userDto){
        userDto.setId(UUID.randomUUID());
        return userRepository.save(UserConverter.convertToUser(userDto));
    }
}
