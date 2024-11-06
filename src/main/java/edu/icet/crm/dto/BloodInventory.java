package edu.icet.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BloodInventory {
    private String inventoryID;
    private String bloodType;
    private Double amount;
    private String expiryDate;
}
