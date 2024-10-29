package edu.icet.crm.repository;

import edu.icet.crm.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AdminRepository extends JpaRepository<AdminEntity,String> {
    AdminEntity findByEmail(String email);
}
