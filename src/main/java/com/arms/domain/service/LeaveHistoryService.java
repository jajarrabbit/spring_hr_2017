package com.arms.domain.service;

import com.arms.app.leavehistory.LeaveHistoryForm;
import com.arms.domain.entity.Employee;
import com.arms.domain.entity.LeaveHistory;
import com.arms.domain.repository.EmployeeRepository;
import com.arms.domain.repository.LeaveHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by arms20170106 on 16/1/2560.
 */
@Service
@Transactional
public class LeaveHistoryService {

    @Autowired
    LeaveHistoryRepository leaveHistoryRepository;

    @Autowired
    EmployeeRepository employeeRepository;

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
        leaveHistory.setReason(leaveHistoryForm.getReason());
        leaveHistory.setRemark(leaveHistoryForm.getRemark());
        leaveHistoryRepository.save(leaveHistory);
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

}
