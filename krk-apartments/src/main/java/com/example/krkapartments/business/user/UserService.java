package com.example.krkapartments.business.user;

import com.example.krkapartments.endpoint.user.dto.UserDto;
import com.example.krkapartments.module.user.UserConverter;
import com.example.krkapartments.persistence.user.entity.UserEntity;
import com.example.krkapartments.persistence.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;
    UserConverter userConverter;

    public UserEntity addUser(UserDto userDto) {
        userDto.setId(UUID.randomUUID());
        return userRepository.save(UserConverter.convertToUser(userDto));
    }
}
