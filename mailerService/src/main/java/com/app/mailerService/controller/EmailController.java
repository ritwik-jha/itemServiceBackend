package com.app.mailerService.controller;


import com.app.mailerService.entity.EmailDetails;
import com.app.mailerService.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/")
    public String sendmail(@RequestBody EmailDetails details){
        return emailService.sendSimpleMail(details);
    }
}
