package com.example.krkapartments.business.user;

import com.example.krkapartments.domain.user.User;
import com.example.krkapartments.domain.user.UserMapper;
import com.example.krkapartments.persistence.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public Optional<User> save(User user) {
        return Optional.ofNullable(user)
                .map(userMapper::mapFromDomainToEntity)
                .map(userRepository::save)
                .map(userMapper::mapFromEntityToDomain);
    }

    @Transactional
    public Optional<User> createUser(User user) {
        return this.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::mapFromEntityToDomain);
    }
}