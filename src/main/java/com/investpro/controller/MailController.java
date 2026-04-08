package com.investpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.investpro.service.EmailService;

@RestController
public class MailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/mail/test")
    public String sendTestMail() {
        emailService.sendEmail("venuthurlanikhithareddy@gmail.com", "Test Mail", "Mail successful ga pampinchabadindi");
        return "Mail sent successfully";
    }
}