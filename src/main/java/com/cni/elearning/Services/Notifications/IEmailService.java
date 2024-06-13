package com.cni.elearning.Services.Notifications;

import com.cni.elearning.Models.Notifications.EmailDetails;

public interface IEmailService {
    // Method 1
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}
