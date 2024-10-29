package edu.icet.crm.controller;

import edu.icet.crm.dto.LoginRequest;
import edu.icet.crm.service.AuthenticationService;
import edu.icet.crm.service.DonarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/Authentication")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
       String role = authenticationService.login(loginRequest);
       if(role.equals("ADMIN")){
           return "Admin Login Successful";
       }else if(role.equals("DONAR")){
           return "Donar Login Successful";
       }else{
           return "Login Failed";
       }
    }
}
