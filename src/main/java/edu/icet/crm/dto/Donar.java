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
public class Donar {
    private String donorID;
    private String name;
    private String address;
    private String city;
    private String email;
    private String password;
    private String contactNumber;
    private String bloodGroup;
    private LocalDate dob;
    private String gender;
    private String age;
}
