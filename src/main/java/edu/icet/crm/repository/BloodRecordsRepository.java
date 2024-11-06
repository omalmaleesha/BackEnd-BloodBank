package edu.icet.crm.repository;

import edu.icet.crm.entity.BloodRecordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodRecordsRepository extends JpaRepository<BloodRecordsEntity,String> {
    BloodRecordsEntity findByRecordID(String recordID);
}
