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
public class BloodRecords {
    private String recordID;
    private String hospitalID;
    private String donarID;
    private LocalDate donationDate;
    private Double amount;

}
