package edu.icet.crm.repository;

import edu.icet.crm.entity.ApprovedRequestEntity;
import edu.icet.crm.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApproveRequestRepository extends JpaRepository<ApprovedRequestEntity,String> {
    ApprovedRequestEntity findByApprovedRequestId(String approvedRequestId);

    List<ApprovedRequestEntity> findByStatus(String completed);
}
