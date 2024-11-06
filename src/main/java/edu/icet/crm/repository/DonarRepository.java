package edu.icet.crm.repository;

import edu.icet.crm.entity.DonarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonarRepository extends JpaRepository<DonarEntity,String> {
    DonarEntity findByEmail(String email);
    DonarEntity findByDonorID(String id);
}
