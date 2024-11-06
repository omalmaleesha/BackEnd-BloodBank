package edu.icet.crm.repository;

import edu.icet.crm.entity.ApprovedRequestEntity;
import edu.icet.crm.entity.BloodInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodInventoryRepository extends JpaRepository<BloodInventoryEntity,String> {
    BloodInventoryEntity findByBloodType(String bloodGroup);

    BloodInventoryEntity findByInventoryID(String inventoryID);
}
