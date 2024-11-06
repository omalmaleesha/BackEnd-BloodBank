package edu.icet.crm.service.impl;

import edu.icet.crm.dto.Appointment;
import edu.icet.crm.entity.AppointmentEntity;
import edu.icet.crm.entity.DonarEntity;
import edu.icet.crm.repository.AppointmentRepository;
import edu.icet.crm.repository.DonarRepository;
import edu.icet.crm.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DonarRepository donarRepository;

    @Override
    public boolean addAppointment(Appointment appointment) {
        appointment.setAppointmentID(AppointmentEntity.generateAppointmentID(getNextSequenceNumber()));
        DonarEntity donorEntity = donarRepository.findByDonorID(appointment.getDonarID());
        AppointmentEntity map = new ModelMapper().map(appointment, AppointmentEntity.class);
        map.setDonarEntity(donorEntity);
        appointmentRepository.save(map);
        AppointmentEntity byId = appointmentRepository.findByAppointmentID(appointment.getAppointmentID());
        return byId.getAppointmentID().equals(appointment.getAppointmentID());
    }
    private int getNextSequenceNumber() {
        return (int)appointmentRepository.count() + 1;
    }

    @Override
    public List<Appointment> getAllAppointments() {

        return List.of();
    }

    @Override
    public Appointment findAppointmentById(String appointmentId) {

        return null;
    }

    @Override
    public boolean deleteAppointmentById(String appointmentId) {
        appointmentRepository.deleteById(appointmentId);
        AppointmentEntity byAppointmentID = appointmentRepository.findByAppointmentID(appointmentId);
        return byAppointmentID==null;
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        return false;
    }

    @Override
    public List<Appointment> findDonarAppointmentsById(String donarID) {
        DonarEntity donorEntity = donarRepository.findById(donarID).orElse(null);
        if (donorEntity == null) {
            return new ArrayList<>();
        }
        List<AppointmentEntity> appointmentEntities = appointmentRepository.findByDonarEntity(donorEntity);

        List<Appointment> appointments = new ArrayList<>();
        appointmentEntities.forEach(entity -> {
            appointments.add(new ModelMapper().map(entity, Appointment.class));
        });
        return appointments;
    }

    @Override
    public List<Appointment> getPendingList() {
        List<AppointmentEntity> appointmentEntities = appointmentRepository.findByStatus("PENDING");
        List<Appointment> appointments = new ArrayList<>();
        appointmentEntities.forEach(obj->{
            appointments.add(new ModelMapper().map(obj,Appointment.class));
        });
        return appointments;
    }

    @Override
    public boolean updateAppointmentStatus(String appointmentId, String status) {
        AppointmentEntity byId = appointmentRepository.findByAppointmentID(appointmentId);

        if (byId != null) {
            byId.setStatus(status);
            appointmentRepository.save(byId);
            return true;
        }

        return false;
    }

    @Override
    public List<Appointment> getApprovedList() {
        List<AppointmentEntity> appointmentEntities = appointmentRepository.findByStatus("APPROVED");
        List<Appointment> appointments = new ArrayList<>();
        appointmentEntities.forEach(obj->{
            appointments.add(new ModelMapper().map(obj,Appointment.class));
        });
        return appointments;
    }
}
