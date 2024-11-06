package edu.icet.crm.service.impl;

import edu.icet.crm.dto.Admin;
import edu.icet.crm.entity.AdminEntity;
import edu.icet.crm.entity.DonarEntity;
import edu.icet.crm.repository.AdminRepository;
import edu.icet.crm.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public Admin findAdminByID(String adminId) {
        return new ModelMapper().map(adminRepository.findByAdminID(adminId),Admin.class);
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        if (adminRepository.existsById(admin.getAdminID())) {
            AdminEntity existingAdminEntity = adminRepository.findByAdminID(admin.getAdminID());

            if (existingAdminEntity != null) {
                existingAdminEntity.setName(admin.getName() != null ? admin.getName() : existingAdminEntity.getName());
                existingAdminEntity.setEmail(admin.getEmail() != null ? admin.getEmail() : existingAdminEntity.getEmail());
                existingAdminEntity.setContactNumber(admin.getContactNumber() != null ? admin.getContactNumber() : existingAdminEntity.getContactNumber());
                if (admin.getPassword() != null && !admin.getPassword().isEmpty()) {
                    existingAdminEntity.setPassword(admin.getPassword());
                }

                adminRepository.save(existingAdminEntity);
                return true;
            }
        }
        return false;
    }
}
