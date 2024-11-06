package edu.icet.crm.repository;

import edu.icet.crm.entity.AppointmentEntity;
import edu.icet.crm.entity.DonarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity,String> {
    AppointmentEntity findByAppointmentID(String appointmentID);

    List<AppointmentEntity> findByDonarEntity(DonarEntity donarEntity);

    List<AppointmentEntity> findByStatus(String pending);
}


