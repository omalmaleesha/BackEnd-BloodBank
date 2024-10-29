package edu.icet.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "AdminEntity")
@Table(name = "AdminEntity")
public class AdminEntity {
    @Id
    private String adminID;
    private String name;
    private String email;
    private String contactNumber;
    private String password;


}
