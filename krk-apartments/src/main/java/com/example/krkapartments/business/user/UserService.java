package com.example.krkapartments.business.user;

import com.example.krkapartments.domain.user.UserMapper;
import com.example.krkapartments.persistence.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    //TODO check if it's needed
//    public UserEntity addUser(UserDto userDto) {
//        userDto.setId(UUID.randomUUID());
//        return userRepository.save(UserConverter.convertToUser(userDto));
//    }
}
