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
@Entity(name = "FeedBacksEntity")
@Table(name = "FeedBacksEntity")
public class FeedBacksEntity {
    @Id
    private String feedbackID;
    private String text;
    private String date;
    private String time;

    public static String generateFeedBackID(int sequenceNumber) {
        return String.format("FED-%03d", sequenceNumber);
    }

}
