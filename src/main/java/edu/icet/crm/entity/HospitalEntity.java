package edu.icet.crm.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "HospitalEntity")
@Table(name = "HospitalEntity")
public class HospitalEntity {
    @Id
    private String hospitalID;
    private String name;
    private String address;
    private String city;
    private String province;
    private String zipCode;
    private String contactNumber;
    private String email;
    private String password;
    private String type;
    private String msg;

    public static String generateHospitalID(int sequenceNumber) {
        return String.format("HOS-%03d", sequenceNumber);
    }
}
