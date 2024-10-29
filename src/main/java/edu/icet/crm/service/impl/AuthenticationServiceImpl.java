package edu.icet.crm.service.impl;

import edu.icet.crm.dto.LoginRequest;
import edu.icet.crm.entity.AdminEntity;
import edu.icet.crm.entity.DonarEntity;
import edu.icet.crm.repository.AdminRepository;
import edu.icet.crm.repository.DonarRepository;
import edu.icet.crm.service.AuthenticationService;
import edu.icet.crm.util.Encrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AdminRepository adminRepository;
    private final DonarRepository donarRepository;

    @Override
    public String login(LoginRequest loginRequest) {
        AdminEntity adminEntity = adminRepository.findByEmail(loginRequest.getEmail());
        if(adminEntity != null && Encrypt.checkPassword(loginRequest.getPassword(),adminEntity.getPassword())){
            return "ADMIN";
        }
        DonarEntity donarEntity = donarRepository.findByEmail(loginRequest.getEmail());
        if (donarEntity != null && Encrypt.checkPassword(loginRequest.getPassword(),donarEntity.getPassword())){
            return "DONAR";
        }
        return null;
    }
}
