package edu.icet.crm.service;

import edu.icet.crm.dto.LoginRequest;

public interface AuthenticationService {
    String login(LoginRequest loginRequest);
}
