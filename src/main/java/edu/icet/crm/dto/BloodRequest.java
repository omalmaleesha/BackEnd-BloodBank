package edu.icet.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BloodRequest {
    private String requestID;
    private String hospitalID;
    private String name;
    private String bloodType;
    private Double amount;
    private String contactNumber;
    private String message;
    private String type;
    private String status;
}
