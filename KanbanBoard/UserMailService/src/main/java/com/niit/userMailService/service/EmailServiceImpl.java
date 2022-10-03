package com.niit.userMailService.service;

import com.niit.userMailService.model.EmailDetails;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Data
@Service
public class EmailServiceImpl implements EmailServices {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendMail(EmailDetails body) {
        System.out.println("abc"+sender);
        System.out.println(body);


        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(body.getReceiver());
            mailMessage.setText(body.getMessageBody());
            mailMessage.setSubject(body.getSubject());

            // Sending the mail
            System.out.println("================================================");
            System.out.println(mailMessage);
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            System.out.println(e);
            return "Error while Sending Mail";
        }
    }
}
