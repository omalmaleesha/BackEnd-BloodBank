package edu.icet.crm.repository;

import edu.icet.crm.entity.BloodRecordsEntity;
import edu.icet.crm.entity.DonarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodRecordsRepository extends JpaRepository<BloodRecordsEntity,String> {
    BloodRecordsEntity findByRecordID(String recordID);

    List<BloodRecordsEntity> findByDonarEntity(DonarEntity byDonorID);
}

