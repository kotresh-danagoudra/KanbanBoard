package com.niit.userMailService.controller;

import com.niit.userMailService.model.EmailDetails;
import com.niit.userMailService.model.Message;
import com.niit.userMailService.service.EmailServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
public class EmailController {
    @Autowired
    private EmailServices emailService;

    // Sending a simple Email
    @PostMapping("/sendMail")
//    @EventListener(ApplicationReadyEvent.class)
    public ResponseEntity<?> sendMail(@RequestBody EmailDetails details) {
        String status
                = emailService.sendMail(details);

        return new ResponseEntity<>(new Message("info",status), HttpStatus.OK);
    }
}
