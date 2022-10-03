package com.niit.userMailService.service;

import com.niit.userMailService.model.EmailDetails;

public interface EmailServices {

    String sendMail(EmailDetails body);

}
