package com.arms.domain.service;

import com.arms.app.HolidayLeave.AnnualLeaveSearch;
import com.arms.domain.entity.HolidayLeave;
import com.arms.domain.repository.HolidayLeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.transaction.Transactional;

/**
 * Created by arms20170106 on 17/2/2560.
 */
@Service
@Transactional
public class EventService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    HolidayLeaveRepository holidayLeaveRepository;

    public void sendCalendar(Integer holId) throws Exception {
        HolidayLeave annualLeave = holidayLeaveRepository.findOne(holId);
        String from = "benz.s@arms-thai.com";
        String to = "serenez@zoho.com";
        MimeMessage message = mailSender.createMimeMessage();
        message.addHeaderLine("method=REQUEST");
        message.addHeaderLine("charset=UTF-8");
        message.addHeaderLine("component=VEVENT");
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Outlook Meeting Request Using JavaMail");
        StringBuffer sb = new StringBuffer();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        StringBuffer buffer = sb.append("BEGIN:VCALENDAR\n" +
                "PRODID:-//Zoho Corporation//Zoho Calendar-US//EN\n" +
                "VERSION:2.0\n" +
                "X-WR-CALNAME:National Holidays\n" +
                "X-WR-TIMEZONE:Asia/Bangkok\n" +
                "CALSCALE:GREGORIAN\n" +
                "METHOD:REQUEST\n" +
                "BEGIN:VEVENT\n" +
                "SUMMARY:" + annualLeave.getHolidayDetail() + "\n" +
                "DESCRIPTION:\n" +
                "DTSTART:" + df.format(annualLeave.getHolidayDate()) + "\n" +
                "DTEND:" + df.format(annualLeave.getHolidayDate()) + "\n" +
                "LOCATION:\n" +
                "CLASS:PUBLIC\n" +
                "IMPORTANT:0\n" +
                "STATUS:0\n" +
                "COMMENT:\n" +
                "UID:" + annualLeave.getHolId() + "holidayleave@arms-thai.com\n" +
                "TRANSP:OPAQUE\n" +
                "PRIORITY:5\n" +
                "CREATED:20170222T064459Z\n" +
                "LAST-MODIFIED:20170222T064459Z\n" +
                "DTSTAMP:20170228T064509Z\n" +
                "ORGANIZER;CN=Sorntad:MAILTO:benz.s@arms-thai.com\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;ROLE=REQ-PARTICIPANT;RSVP=TRUE;PARTSTAT=NEEDS-ACTION:MAILTO:serenez@zoho.com\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR"
        );
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setHeader("Content-Class", "urn:content-  classes:calendarmessage");
        messageBodyPart.setHeader("Content-ID", "calendar_message");
        messageBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(buffer.toString(), "text/calendar")));// very important
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        mailSender.send(message);
    }

    public void sendAllMail(AnnualLeaveSearch annualLeaveSearch) throws Exception {
        List<HolidayLeave> holidayLeaveList = holidayLeaveRepository.findAllByHolidayDate(annualLeaveSearch.getYear());
        for (HolidayLeave holidayList : holidayLeaveList) {
            String from = "benz.s@arms-thai.com";
            String to = "serenez@zoho.com";
            MimeMessage message = mailSender.createMimeMessage();
            message.addHeaderLine("method=REQUEST");
            message.addHeaderLine("charset=UTF-8");
            message.addHeaderLine("component=VEVENT");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Outlook Meeting Request Using JavaMail");
            StringBuffer sb = new StringBuffer();
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            StringBuffer buffer = sb.append("BEGIN:VCALENDAR\n" +
                    "PRODID:-//Zoho Corporation//Zoho Calendar-US//EN\n" +
                    "VERSION:2.0\n" +
                    "X-WR-CALNAME:National Holidays\n" +
                    "X-WR-TIMEZONE:Asia/Bangkok\n" +
                    "CALSCALE:GREGORIAN\n" +
                    "METHOD:REQUEST\n" +
                    "BEGIN:VEVENT\n" +
                    "SUMMARY:" + holidayList.getHolidayDetail() + "\n" +
                    "DESCRIPTION:\n" +
                    "DTSTART:" + df.format(holidayList.getHolidayDate()) + "\n" +
                    "DTEND:" + df.format(holidayList.getHolidayDate()) + "\n" +
                    "LOCATION:\n" +
                    "CLASS:PUBLIC\n" +
                    "IMPORTANT:0\n" +
                    "STATUS:0\n" +
                    "COMMENT:\n" +
                    "UID:" + holidayList.getHolId() + "holidayleave@arms-thai.com\n" +
                    "TRANSP:OPAQUE\n" +
                    "PRIORITY:5\n" +
                    "CREATED:20170222T064459Z\n" +
                    "LAST-MODIFIED:20170222T064459Z\n" +
                    "DTSTAMP:20170228T064509Z\n" +
                    "ORGANIZER;CN=Sorntad:MAILTO:benz.s@arms-thai.com\n" +
                    "ATTENDEE;CUTYPE=INDIVIDUAL;ROLE=REQ-PARTICIPANT;RSVP=TRUE;PARTSTAT=NEEDS-ACTION:MAILTO:serenez@zoho.com\n" +
                    "END:VEVENT\n" +
                    "END:VCALENDAR"
            );
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setHeader("Content-Class", "urn:content-  classes:calendarmessage");
            messageBodyPart.setHeader("Content-ID", "calendar_message");
            messageBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(buffer.toString(), "text/calendar")));// very important
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            mailSender.send(message);
        }
    }
}