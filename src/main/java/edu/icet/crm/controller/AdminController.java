package edu.icet.crm.controller;

import edu.icet.crm.dto.Admin;
import edu.icet.crm.dto.Donar;
import edu.icet.crm.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/Admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/findById/{adminId}")
    public Admin getAdminById(@PathVariable String adminId){
        return adminService.findAdminByID(adminId);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAdmin(@RequestBody Admin admin){
        return adminService.updateAdmin(admin)?ResponseEntity
                .status(HttpStatus.OK)
                .body("Admin Updated"):ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to update admin");
    }
}
