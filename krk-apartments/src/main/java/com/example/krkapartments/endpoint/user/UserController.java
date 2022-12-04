package com.example.krkapartments.endpoint.user;

import com.example.krkapartments.business.user.UserService;
import com.example.krkapartments.domain.user.UserMapper;
import com.example.krkapartments.endpoint.user.dto.UserCreateCommand;
import com.example.krkapartments.endpoint.user.dto.UserDto;
import com.example.krkapartments.endpoint.user.exception.UserCreationException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserCreateCommand command) {
        return Optional.ofNullable(command)
                .map(userMapper::mapFromCreateCommandToDomain)
                .flatMap(userService::createUser)
                .map(userMapper::mapFromDomainToDto)
                .orElseThrow(UserCreationException::new);
    }
}
