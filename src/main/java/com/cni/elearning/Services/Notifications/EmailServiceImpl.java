package com.cni.elearning.Services.Notifications;

import com.cni.elearning.Models.Notifications.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements IEmailService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;

    @Override
    public String sendSimpleMail(EmailDetails details) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(sender);
            helper.setTo(details.getRecipient());
            helper.setSubject(details.getSubject());
            helper.setText(details.getMsgBody(), true); // true indicates HTML content

            javaMailSender.send(mimeMessage);
            return "Mail Sent Successfully...";
        } catch (MessagingException e) {
            return "Error while Sending Mail: " + e.getMessage();
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(sender);
            helper.setTo(details.getRecipient());
            helper.setSubject(details.getSubject());
            helper.setText(details.getMsgBody(), true); // true indicates HTML content

            FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
            helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

            javaMailSender.send(mimeMessage);
            return "Mail Sent Successfully...";
        } catch (MessagingException e) {
            return "Error while Sending Mail: " + e.getMessage();
        }
    }
}
