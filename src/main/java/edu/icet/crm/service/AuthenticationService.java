package edu.icet.crm.service;

import edu.icet.crm.dto.LoginRequest;
import edu.icet.crm.dto.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(LoginRequest loginRequest);
}
