package com.arms.domain.service;

import groovy.time.BaseDuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by arms20170106 on 16/2/2560.
 */
@Service
public class MailService {
    private JavaMailSender javaMailSender;
    @Autowired
    public MailService(JavaMailSender javaMailSender)
    {
        this.javaMailSender = javaMailSender;
    }
    public void send()throws MailException
    {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("sorntadza@gmail.com");
        mail.setSubject("Test");
        mail.setText("Hello");
        javaMailSender.send(mail);
    }



}
