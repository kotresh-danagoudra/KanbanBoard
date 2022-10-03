package com.niit.userMailService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    private String receiver;
    private String messageBody;
    private String subject;
    private String attachment;
}
