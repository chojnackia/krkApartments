package com.example.krkapartments.module.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User addUser(UserDto userDto) {

         return userRepository.save(UserConverter.convertToUser(userDto));
    }

}
