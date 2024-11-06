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
public class ApprovedRequest {
    private String approvedRequestId;
    private String donarID;
    private String hospitalID;
    private String appointmentID;
    private Double amount;
    private LocalDate date;
    private String status;
}
