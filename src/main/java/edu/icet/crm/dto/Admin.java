package edu.icet.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {
    private String adminID;
    private String name;
    private String email;
    private String contactNumber;
    private String password;

}
