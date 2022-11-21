package com.example.krkapartments.endpoint.user;

import com.example.krkapartments.business.user.UserService;
import com.example.krkapartments.endpoint.user.dto.UserDto;
import com.example.krkapartments.persistence.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping("/")
//    public UserDto addUser(@Valid @RequestBody UserDto userDto) {
//        UserEntity userEntity = userService.addUser(userDto);
//        return UserConverter.convertUserToDto(userEntity);
//    }
}
