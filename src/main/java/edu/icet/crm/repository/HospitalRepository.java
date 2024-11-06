package edu.icet.crm.repository;

import edu.icet.crm.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<HospitalEntity,String> {
    HospitalEntity findByEmail(String email);

    HospitalEntity findByHospitalID(String hospitalID);

}
