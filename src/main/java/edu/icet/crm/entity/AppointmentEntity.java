package edu.icet.crm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "AppointmentEntity")
@Table(name = "AppointmentEntity")
public class AppointmentEntity {
    @Id
    private String appointmentID;

    @ManyToOne
    @JoinColumn(name = "donorId", nullable = false)
    private DonarEntity donarEntity;

    private String name;
    private String email;
    private String contactNumber;
    private String address;
    private String bloodType;
    private String preferDate;
    private String status;
    private String remarks;

    public static String generateAppointmentID(int sequenceNumber) {
        return String.format("APT-%03d", sequenceNumber);
    }
}
