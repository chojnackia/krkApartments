package com.example.krkapartments.business.admin;

import com.example.krkapartments.endpoint.admin.dto.AdminCreateCommand;
import com.example.krkapartments.endpoint.admin.dto.AdminDto;
import com.example.krkapartments.endpoint.admin.exception.AdminAlreadyExistException;
import com.example.krkapartments.exception.InvalidEmailException;
import com.example.krkapartments.exception.InvalidUserOrPasswordException;
import com.example.krkapartments.module.admin.*;
import com.example.krkapartments.persistence.admin.entity.AdminEntity;
import com.example.krkapartments.persistence.admin.repository.AdminRepository;
import com.example.krkapartments.persistence.shared.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminService {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

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

    public List<AdminDto> getAdminList() {
        List<AdminEntity> adminEntityList = adminRepository.findAll();
        return AdminConverter.entityToDto(adminEntityList);
    }

    public AdminDto registerNewAdminAccount(AdminCreateCommand adminCreateCommand) {
        validateEmail(adminCreateCommand.getEmail());
        validatePassword(adminCreateCommand.getPassword());

        AdminEntity adminEntity = AdminEntity.builder()
                .id(UUID.randomUUID())
                .firstName(adminCreateCommand.getFirstName())
                .lastName(adminCreateCommand.getLastName())
                .active(true)
                .password(passwordEncoder.encode(adminCreateCommand.getPassword()))
                .role(UserRole.ROLE_ADMIN)
                .email(adminCreateCommand.getEmail())
                .build();
        return AdminConverter.convertToAdminDto(adminRepository.save(adminEntity));
    }


}
