package edu.icet.crm.repository;

import edu.icet.crm.entity.AppointmentEntity;
import edu.icet.crm.entity.BloodRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodRequestRepository extends JpaRepository<BloodRequestEntity,String> {
    BloodRequestEntity findByRequestID(String requestID);

    List<BloodRequestEntity> findByStatus(String pending);
}
