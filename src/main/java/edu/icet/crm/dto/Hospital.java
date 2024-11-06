package edu.icet.crm.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hospital {
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
}
