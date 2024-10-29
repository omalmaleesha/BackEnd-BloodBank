package edu.icet.crm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "DonarEntity")
@Table(name = "DonarEntity")
public class DonarEntity {
    @Id
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
