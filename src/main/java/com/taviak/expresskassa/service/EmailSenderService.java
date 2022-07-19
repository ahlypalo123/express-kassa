package com.taviak.expresskassa.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    private final JavaMailSender javaMailSender;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String message, String receiver, String subject) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom("ahlypalo123@gmail.com");
        smm.setText(message);
        smm.setTo(receiver);
        smm.setSubject(subject);

        javaMailSender.send(smm);
    }
}