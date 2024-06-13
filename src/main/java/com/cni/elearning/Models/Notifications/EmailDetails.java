package com.cni.elearning.Models.Notifications;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailDetails  {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
    private String contentType = "text/html";
    
}
