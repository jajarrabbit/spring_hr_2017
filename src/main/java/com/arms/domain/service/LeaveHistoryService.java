package com.arms.domain.service;

import com.arms.app.leavehistory.LeaveHistoryDetailForm;
import com.arms.app.leavehistory.LeaveHistoryEditForm;
import com.arms.app.leavehistory.LeaveHistoryForm;
import com.arms.domain.entity.CompDetail;
import com.arms.domain.entity.Employee;
import com.arms.domain.entity.LeaveHistory;
import com.arms.domain.entity.LeaveType;
import com.arms.domain.repository.CompDetailRepository;
import com.arms.domain.repository.EmployeeRepository;
import com.arms.domain.repository.LeaveHistoryRepository;
import com.arms.domain.repository.LeaveTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by arms20170106 on 16/1/2560.
 */
@Service
@Transactional
public class LeaveHistoryService {
    @Autowired
    LeaveTypeRepository leaveTypeRepository;
    @Autowired
    LeaveHistoryRepository leaveHistoryRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CompDetailRepository compDetailRepository;
    @Autowired
    private JavaMailSender mailSender;


    public void save(LeaveHistoryForm leaveHistoryForm) {
        SimpleDateFormat formatt = new SimpleDateFormat("yyyy/MM/dd");
        LeaveHistory leaveHistory = new LeaveHistory();
        leaveHistory.setEmpId(leaveHistoryForm.getEmpId());
        try{
            leaveHistory.setPeriodFrom(formatt.parse(leaveHistoryForm.getPeriodFrom()));
        }catch(ParseException ex){}
        try{
            leaveHistory.setPeriodUntil(formatt.parse(leaveHistoryForm.getPeriodUntil()));
        }catch(ParseException ex){}
        leaveHistory.setCategoryId(leaveHistoryForm.getCategoryId());
        leaveHistory.setFullday(leaveHistoryForm.getFullday());
        leaveHistory.setHalfday(leaveHistoryForm.getHalfday());
        leaveHistory.setReason(leaveHistoryForm.getReason());
        leaveHistory.setRemark(leaveHistoryForm.getRemark());
        leaveHistoryRepository.save(leaveHistory);
        if(leaveHistoryForm.getCategoryId() == 2) {
            Date  comps = compDetailRepository.CompByEmpId(leaveHistoryForm.getEmpId());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String minDate = df.format(comps);
            List<CompDetail> comp = compDetailRepository.findAll();

            for( CompDetail compUse : comp )
            {
                String date = df.format(compUse.getCompAddDate());
                if (date.equals(minDate))
               {
                   CompDetail compDetail = compDetailRepository.findOne(compUse.getCompDetailId());
                   compDetail.setIsUsed(1);
                   compDetailRepository.save(compDetail);
               }
            }

        }
    }
    public HashMap<String, String> getHireDate(String empId){
        HashMap<String, String> returnMap = new HashMap<>();
        Employee hd = employeeRepository.findOne(Integer.parseInt(empId));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (hd != null){
            returnMap.put("hire_date", formatter.format(hd.getHireDate()).toString());
        }else{
            returnMap.put("hire_date", "");
        }
        return returnMap;
    }

    public int checkDateEdit(LeaveHistoryEditForm leaveHistoryEditForm)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try{
            Date date1 = format.parse(leaveHistoryEditForm.getPeriodFrom());
            Date date2 = format.parse(leaveHistoryEditForm.getPeriodUntil());
            if(date1.before(date2))
            {
                return 1;
            }
            if (date1.after(date2))
            {
                return 2;
            }
        }catch (ParseException ex){}
        return  0;
    }

    public int checkDateInput(LeaveHistoryForm leaveHistoryForm)
    {
        SimpleDateFormat formatt = new SimpleDateFormat("yyyy/MM/dd");

        try{
            Date date1 = formatt.parse(leaveHistoryForm.getPeriodFrom());
            Date date2 = formatt.parse(leaveHistoryForm.getPeriodUntil());
            if(date1.before(date2))
            {
                return  1;
            }
            if(date1.after(date2))
            {
                return 2;
            }

        }catch(ParseException ex){}

        return 0;
    }

    public int checkAmountEdit(LeaveHistoryEditForm leaveHistoryEditForm)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try{
            Date date1 = format.parse(leaveHistoryEditForm.getPeriodFrom());
            Date date2 = format.parse(leaveHistoryEditForm.getPeriodUntil());
            long diff = date2.getTime()-date1.getTime();
            long diffDays = ((diff / (24 * 60 * 60 * 1000))+1);
            long day = leaveHistoryEditForm.getFullday()+leaveHistoryEditForm.getHalfday();

            if(diffDays != day)
            {
                return 1;
            }

        }catch(ParseException ex){}

        return 0;

    }

    public int checkAmount(LeaveHistoryForm leaveHistoryForm)
    {
        SimpleDateFormat formatt = new SimpleDateFormat("yyyy/MM/dd");

        try{
            Date date1 = formatt.parse(leaveHistoryForm.getPeriodFrom());
            Date date2 = formatt.parse(leaveHistoryForm.getPeriodUntil());
            long diff = date2.getTime()-date1.getTime();
            long diffDays = ((diff / (24 * 60 * 60 * 1000))+1);
            long day = leaveHistoryForm.getFullday()+leaveHistoryForm.getHalfday();

            if(diffDays != day)
            {
                return 1;
            }

        }catch(ParseException ex){}

        return 0;
    }
    public LeaveHistoryDetailForm getHistoryDetailByLeaveId(Integer leaveId) {
        LeaveHistoryDetailForm view = new LeaveHistoryDetailForm();
        LeaveHistory leave = leaveHistoryRepository.findOne(leaveId);
        Employee employee = employeeRepository.findOne(leave.getEmpId());
        view.setFirstName(employee.getFirstName());
        view.setLastName(employee.getLastName());
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        view.setHireDate(df.format(employee.getHireDate()));
        view.setPeriodFrom(df.format(leave.getPeriodFrom()));
        view.setPeriodUntil(df.format(leave.getPeriodUntil()));
        view.setFullday(leave.getFullday());
        view.setHalfday(leave.getHalfday());
        view.setTotal((leave.getFullday()+((double)leave.getHalfday()/2)));
        LeaveType type = leaveTypeRepository.findOne(leave.getCategoryId());
        view.setCategoryName(type.getCategoryName());
        view.setReason(leave.getReason());
        view.setRemark(leave.getRemark());
        return view;
    }

    public LeaveHistoryEditForm getHistoryEditByLeaveId(Integer leaveId) {
        LeaveHistoryEditForm view = new LeaveHistoryEditForm();
        LeaveHistory leave = leaveHistoryRepository.findOne(leaveId);
        view.setLeaveId(leave.getLeaveId());
        view.setEmpId(leave.getEmpId());
        Employee employee = employeeRepository.findOne(leave.getEmpId());
        view.setFirstName(employee.getFirstName());
        view.setLastName(employee.getLastName());
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        view.setHireDate(df.format(employee.getHireDate()));
        view.setPeriodFrom(df.format(leave.getPeriodFrom()));
        view.setPeriodUntil(df.format(leave.getPeriodUntil()));
        view.setFullday(leave.getFullday());
        view.setHalfday(leave.getHalfday());
        view.setCategoryId(leave.getCategoryId());
        view.setReason(leave.getReason());
        view.setRemark(leave.getRemark());
        return view;
    }

    public void update(LeaveHistoryEditForm leaveHistoryEditForm) {
        LeaveHistory leaveHistory = leaveHistoryRepository.findOne(leaveHistoryEditForm.getLeaveId());
        leaveHistory.setEmpId(leaveHistoryEditForm.getEmpId());
        SimpleDateFormat formattt = new SimpleDateFormat("yyyy/MM/dd");
        try{
            leaveHistory.setPeriodFrom(formattt.parse(leaveHistoryEditForm.getPeriodFrom()));
        }catch(ParseException ex){}
        try{
            leaveHistory.setPeriodUntil(formattt.parse(leaveHistoryEditForm.getPeriodUntil()));
        }catch(ParseException ex){}
        leaveHistory.setCategoryId(leaveHistoryEditForm.getCategoryId());
        leaveHistory.setReason(leaveHistoryEditForm.getReason());
        leaveHistory.setRemark(leaveHistoryEditForm.getRemark());
        leaveHistory.setFullday(leaveHistoryEditForm.getFullday());
        leaveHistory.setHalfday(leaveHistoryEditForm.getHalfday());
        leaveHistoryRepository.save(leaveHistory);
    }

    public  void delete(Integer leaveId) {
        leaveHistoryRepository.delete(leaveId);
    }

    public void  sendMail() throws Exception {
        Integer leaveHistorys = leaveHistoryRepository.findMaxLeaveId();
        LeaveHistory leaveHistory = leaveHistoryRepository.findOne(leaveHistorys);
        Employee employee = employeeRepository.findOne(leaveHistory.getEmpId());
        LeaveType leaveType = leaveTypeRepository.findOne(leaveHistory.getCategoryId());
        String from = "benz.s@arms-thai.com";
        String to = employee.getEmail();
        MimeMessage message = mailSender.createMimeMessage();
        message.addHeaderLine("method=REQUEST");
        message.addHeaderLine("charset=UTF-8");
        message.addHeaderLine("component=VEVENT");
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Leave Detail");
        StringBuffer sb = new StringBuffer();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        StringBuffer buffer = sb.append("BEGIN:VCALENDAR\n" +
                "PRODID:-//Zoho Corporation//Zoho Calendar-US//EN\n" +
                "VERSION:2.0\n" +
                "X-WR-CALNAME:Leave Day\n"+
                "X-WR-TIMEZONE:Asia/Bangkok\n" +
                "CALSCALE:GREGORIAN\n" +
                "METHOD:REQUEST\n" +
                "BEGIN:VEVENT\n" +
                "SUMMARY:" + leaveType.getCategoryName()+"\n"+
                "DESCRIPTION:" + leaveHistory.getReason()  + "\n" +
                "DTSTART:"+ df.format(leaveHistory.getPeriodFrom()) +"T000000Z\n"+
                "DTEND:" + df.format(leaveHistory.getPeriodUntil()) +"T010000Z\n"+
                "LOCATION:\n" +
                "CLASS:PUBLIC\n" +
                "IMPORTANT:0\n" +
                "STATUS:CONFIRMED \n"+
                "COMMENT:\n" +
                "UID:"+leaveHistory.getEmpId()+leaveHistory.getPeriodFrom()+"holidayleave@arms-thai.com\n" +
                "TRANSP:OPAQUE\n" +
                "PRIORITY:5\n" +
                "CREATED:20170222T064459Z\n" +
                "LAST-MODIFIED:20170222T064459Z\n" +
                "DTSTAMP:20170228T064509Z\n" +
                "ORGANIZER;CN=Sorntad:MAILTO:benz.s@arms-thai.com\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;ROLE=REQ-PARTICIPANT;RSVP=TRUE;PARTSTAT=NEEDS-ACTION:MAILTO:"+employee.getEmail()+"\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;ROLE=REQ-PARTICIPANT;PARTSTAT=ACCEPTED:MAILTO:benz.s@arms-thai.com\n"+
                "END:VEVENT\n" +
                "END:VCALENDAR"
        );
        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();
        // Fill the message
        messageBodyPart.setHeader("Content-Class", "urn:content-  classes:calendarmessage");
        messageBodyPart.setHeader("Content-ID", "calendar_message");
        messageBodyPart.setDataHandler(new DataHandler(
                new ByteArrayDataSource(buffer.toString(), "text/calendar")));// very important
        // Create a Multipart
        Multipart multipart = new MimeMultipart();
        // Add part one
        multipart.addBodyPart(messageBodyPart);
        // Put parts in message
        message.setContent(multipart);
        // send message
        mailSender.send(message);
    }

    public void cancelMail(Integer leaveId) throws  Exception
    {
        LeaveHistory leaveHistory = leaveHistoryRepository.findOne(leaveId);
        Employee employee = employeeRepository.findOne(leaveHistory.getEmpId());
        LeaveType leaveType = leaveTypeRepository.findOne(leaveHistory.getEmpId());
        String from = "benz.s@arms-thai.com";
        String to = employee.getEmail();
        MimeMessage message = mailSender.createMimeMessage();
        message.addHeaderLine("method=CANCEL");
        message.addHeaderLine("charset=UTF-8");
        message.addHeaderLine("component=VEVENT");
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Leave Detail");
        StringBuffer sb = new StringBuffer();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        StringBuffer buffer = sb.append("BEGIN:VCALENDAR\n" +
                "PRODID:-//Zoho Corporation//Zoho Calendar-US//EN\n" +
                "VERSION:2.0\n" +
                "X-WR-CALNAME:Leave Day\n"+
                "X-WR-TIMEZONE:Asia/Bangkok\n" +
                "CALSCALE:GREGORIAN\n" +
                "METHOD:CANCEL\n" +
                "BEGIN:VEVENT\n" +
                "SUMMARY:" + leaveType.getCategoryName()+"\n"+
                "DESCRIPTION:" + leaveHistory.getReason()  + "\n" +
                "DTSTART:"+ df.format(leaveHistory.getPeriodFrom()) +"\n"+
                "DTEND:" + df.format(leaveHistory.getPeriodUntil()) +"\n"+
                "LOCATION:\n" +
                "CLASS:PUBLIC\n" +
                "IMPORTANT:0\n" +
                "STATUS: CANCELLED\n"+
                "COMMENT:\n" +
                "UID:"+leaveHistory.getEmpId()+leaveHistory.getPeriodFrom()+"holidayleave@arms-thai.com\n" +
                "TRANSP:OPAQUE\n" +
                "PRIORITY:5\n" +
                "CREATED:20170222T064459Z\n" +
                "LAST-MODIFIED:20170222T064459Z\n" +
                "DTSTAMP:20170228T064509Z\n" +
                "ORGANIZER;CN=Sorntad:MAILTO:benz.s@arms-thai.com\n" +
                "ATTENDEE;CUTYPE=INDIVIDUAL;ROLE=REQ-PARTICIPANT;RSVP=TRUE;PARTSTAT=DECLINED:MAILTO:"+employee.getEmail()+"\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR"
        );
        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();
        // Fill the message
        messageBodyPart.setHeader("Content-Class", "urn:content-  classes:calendarmessage");
        messageBodyPart.setHeader("Content-ID", "calendar_message");
        messageBodyPart.setDataHandler(new DataHandler(
                new ByteArrayDataSource(buffer.toString(), "text/calendar")));// very important
        // Create a Multipart
        Multipart multipart = new MimeMultipart();
        // Add part one
        multipart.addBodyPart(messageBodyPart);
        // Put parts in message
        message.setContent(multipart);
        // send message
        mailSender.send(message);
    }
}