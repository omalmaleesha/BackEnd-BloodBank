package edu.icet.crm.service.impl;

import edu.icet.crm.dto.LoginRequest;
import edu.icet.crm.dto.LoginResponse;
import edu.icet.crm.entity.AdminEntity;
import edu.icet.crm.entity.DonarEntity;
import edu.icet.crm.entity.HospitalEntity;
import edu.icet.crm.repository.AdminRepository;
import edu.icet.crm.repository.DonarRepository;
import edu.icet.crm.repository.HospitalRepository;
import edu.icet.crm.service.AuthenticationService;
import edu.icet.crm.util.Encrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    AdminRepository adminRepository;
    private final DonarRepository donarRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        AdminEntity adminEntity = adminRepository.findByEmail(loginRequest.getEmail());
        if(adminEntity != null && loginRequest.getPassword().equals(adminEntity.getPassword())){
            return new LoginResponse(adminEntity.getAdminID(),"ADMIN");
        }
        DonarEntity donarEntity = donarRepository.findByEmail(loginRequest.getEmail());
        if (donarEntity != null && Encrypt.checkPassword(loginRequest.getPassword(),donarEntity.getPassword())){
            return new LoginResponse(donarEntity.getDonorID(),"DONAR");
        }
        HospitalEntity hospitalEntity = hospitalRepository.findByEmail(loginRequest.getEmail());
        if (hospitalEntity != null && Encrypt.checkPassword(loginRequest.getPassword(),hospitalEntity.getPassword())){
            return new LoginResponse(hospitalEntity.getHospitalID(),"HOSPITAL");
        }
        return null;
    }
}
