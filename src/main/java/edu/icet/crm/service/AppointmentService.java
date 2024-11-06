package edu.icet.crm.service;

import edu.icet.crm.dto.Appointment;

import java.util.List;

public interface AppointmentService {
    boolean addAppointment(Appointment appointment);

    List<Appointment> getAllAppointments();

    Appointment findAppointmentById(String appointmentId);

    boolean deleteAppointmentById(String appointmentId);

    boolean updateAppointment(Appointment appointment);

    List<Appointment> findDonarAppointmentsById(String donarID);

    List<Appointment> getPendingList();

    boolean updateAppointmentStatus(String appointmentId, String status);

    List<Appointment> getApprovedList();

}
