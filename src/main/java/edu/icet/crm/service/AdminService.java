package edu.icet.crm.service;

import edu.icet.crm.dto.Admin;

public interface AdminService {
    Admin findAdminByID(String adminId);

    boolean updateAdmin(Admin admin);

}
