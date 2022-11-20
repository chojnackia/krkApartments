package com.example.krkapartments.module.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public UserDto addUser(@Valid @RequestBody UserDto userDto) {
        UserEntity userEntity = userService.addUser(userDto);
        return UserConverter.convertUserToDto(userEntity);
    }
}
