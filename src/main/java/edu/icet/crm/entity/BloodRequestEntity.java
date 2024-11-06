package edu.icet.crm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "BloodRequestEntity")
@Table(name = "BloodRequestEntity")
public class BloodRequestEntity {
    @Id
    private String requestID;

    @ManyToOne
    @JoinColumn(name = "hospitalID", nullable = false)
    private HospitalEntity hospitalEntity;
    private String name;
    private String bloodType;
    private Double amount;
    private String contactNumber;
    private String message;
    private String type;
    private String status;

    public static String generateBloodRequestID(int sequenceNumber) {
        return String.format("REQ-%03d", sequenceNumber);
    }
}
