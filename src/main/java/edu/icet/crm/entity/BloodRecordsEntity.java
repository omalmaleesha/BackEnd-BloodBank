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
@Entity(name = "BloodRecordsEntity")
@Table(name = "BloodRecordsEntity")
public class BloodRecordsEntity {
    @Id
    private String recordID;

    @ManyToOne
    @JoinColumn(name = "hospitalID", nullable = false)
    private HospitalEntity hospitalEntity;

    @ManyToOne
    @JoinColumn(name = "donorID", nullable = false)
    private DonarEntity donarEntity;

    private LocalDate donationDate;
    private Double amount;


    public static String generateBloodRecordID(int sequenceNumber) {
        return String.format("BREC-%03d", sequenceNumber);
    }

}
