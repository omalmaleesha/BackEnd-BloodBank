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
@Entity(name = "ApprovedRequestEntity")
@Table(name = "ApprovedRequestEntity")
public class ApprovedRequestEntity {
    @Id
    private String approvedRequestId;

    @ManyToOne
    @JoinColumn(name = "donorId", nullable = false)
    private DonarEntity donarEntity;

    @ManyToOne
    @JoinColumn(name = "hospitalID", nullable = false)
    private HospitalEntity hospitalEntity;

    @ManyToOne
    @JoinColumn(name = "appointmentID", nullable = false)
    private AppointmentEntity appointmentEntity;

    private Double amount;
    private LocalDate date;
    private String status;

    public static String generateApprovedRequestID(int sequenceNumber) {
        return String.format("AREQ-%03d", sequenceNumber);
    }
}
