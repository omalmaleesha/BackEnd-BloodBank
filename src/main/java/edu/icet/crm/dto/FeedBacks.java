package edu.icet.crm.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeedBacks {
    private String feedbackID;
    private String text;
    private String date;
    private String time;
}
