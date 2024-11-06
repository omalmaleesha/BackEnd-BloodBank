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
@Entity(name = "BloodInventoryEntity")
@Table(name = "BloodInventoryEntity")
public class BloodInventoryEntity {
    @Id
    private String inventoryID;
    private String bloodType;
    private Double amount;
    private String expiryDate;

    public static String generateInventoryID(int sequenceNumber) {
        return String.format("INV-%03d", sequenceNumber);
    }

}
