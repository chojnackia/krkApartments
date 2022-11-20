package com.example.krkapartments.endpoint.admin;

import com.example.krkapartments.business.admin.AdminService;
import com.example.krkapartments.domain.admin.AdminMapper;
import com.example.krkapartments.endpoint.admin.dto.AdminDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final AdminMapper adminMapper;

    @GetMapping
    public List<AdminDto> getAdmins() {
        return adminService.getAdminList()
                .stream()
                .map(adminMapper::mapFromDomainToDto)
                .collect(Collectors.toList());
    }
}
