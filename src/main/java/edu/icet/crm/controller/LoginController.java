package edu.icet.crm.controller;

import edu.icet.crm.dto.LoginRequest;
import edu.icet.crm.dto.LoginResponse;
import edu.icet.crm.service.AuthenticationService;
import edu.icet.crm.service.DonarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/Authentication")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
       LoginResponse role = authenticationService.login(loginRequest);
        return switch (role.getType()) {
            case "ADMIN" -> ResponseEntity.ok(role);
            case "DONAR" -> ResponseEntity.ok(role);
            case "HOSPITAL" -> ResponseEntity.ok(role);
            default -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        };
    }
}
