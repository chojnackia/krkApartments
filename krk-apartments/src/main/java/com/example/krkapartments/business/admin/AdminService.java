package com.example.krkapartments.business.admin;

import com.example.krkapartments.domain.admin.Admin;
import com.example.krkapartments.domain.admin.AdminMapper;
import com.example.krkapartments.endpoint.admin.dto.AdminCreateCommand;
import com.example.krkapartments.endpoint.admin.exception.AdminAlreadyExistException;
import com.example.krkapartments.exception.InvalidEmailException;
import com.example.krkapartments.exception.InvalidUserOrPasswordException;
import com.example.krkapartments.persistence.admin.entity.AdminEntity;
import com.example.krkapartments.persistence.admin.repository.AdminRepository;
import com.example.krkapartments.persistence.shared.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminMapper adminMapper;

    private void validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (!matcher.find()) {
            throw new InvalidEmailException("Invalid email provided: " + email);
        }
        if (adminRepository.findByEmailAllIgnoreCase(email).isPresent()) {
            throw new AdminAlreadyExistException("User with the following email already exists: " + email);
        }
    }

    private void validatePassword(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        if (!matcher.find()) {
            throw new InvalidUserOrPasswordException("Invalid password: " + password + ". " +
                    "Password has to have at least one upper case English letter, at least one lower case English letter,"
                    + " at least one digit, at least one special character, minimum eight in length");
        }
    }

    public List<Admin> getAdminList() {
        return adminRepository.findAll()
                .stream()
                .map(adminMapper::mapFromEntityToDomain)
                .collect(Collectors.toList());
    }

    public Optional<Admin> registerNewAdminAccount(AdminCreateCommand adminCreateCommand) {
        return Optional.ofNullable(adminCreateCommand)
                .map(acc -> {
                    validateEmail(acc.getEmail());
                    validatePassword(acc.getPassword());
                    return AdminEntity.builder()
                            .id(UUID.randomUUID())
                            .firstName(adminCreateCommand.getFirstName())
                            .lastName(adminCreateCommand.getLastName())
                            .active(true)
                            .password(passwordEncoder.encode(adminCreateCommand.getPassword()))
                            .role(UserRole.ROLE_ADMIN)
                            .email(adminCreateCommand.getEmail())
                            .build();
                })
                .map(adminRepository::save)
                .map(adminMapper::mapFromEntityToDomain);
    }


}
