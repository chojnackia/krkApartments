package com.example.krkapartments.security;

import com.example.krkapartments.business.admin.AdminService;
import com.example.krkapartments.domain.admin.AdminMapper;
import com.example.krkapartments.endpoint.admin.dto.AdminCreateCommand;
import com.example.krkapartments.endpoint.admin.dto.AdminDto;
import com.example.krkapartments.endpoint.admin.exception.AdminCreationException;
import com.example.krkapartments.persistence.admin.entity.AdminEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final AdminService adminService;
    private final AdminMapper adminMapper;

    @PostMapping("/login")
    public ResponseEntity<AdminDto> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );
            AdminEntity adminEntity = (AdminEntity) authentication.getPrincipal();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtTokenUtil.generateAccessToken(adminEntity))
                    .body(adminMapper.mapFromEntityToDto(adminEntity));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AdminDto registerNewAdminAccount(@RequestBody AdminCreateCommand adminCreateCommand) {
        return adminService.registerNewAdminAccount(adminCreateCommand)
                .map(adminMapper::mapFromDomainToDto)
                .orElseThrow(AdminCreationException::new);
    }


}
