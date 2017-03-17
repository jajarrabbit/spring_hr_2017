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
import org.springframework.stereotype.Service;

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
}
