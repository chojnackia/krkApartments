package com.example.krkapartments.endpoint.admin;

import com.example.krkapartments.business.admin.AdminService;
import com.example.krkapartments.endpoint.admin.dto.AdminDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/")
    public List<AdminDto> getAdmins() {
        return adminService.getAdminList();
    }
}
