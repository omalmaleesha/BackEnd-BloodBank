package edu.icet.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Appointment {
    private String appointmentID;
    private String donarID;
    private String name;
    private String email;
    private String contactNumber;
    private String address;
    private String bloodType;
    private String preferDate;
    private String status;
    private String remarks;


}
