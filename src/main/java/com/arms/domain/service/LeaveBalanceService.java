package com.arms.domain.service;


import com.arms.domain.entity.*;
import com.arms.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

/**
 * Created by arms20170106 on 2/3/2560.
 */
@Service
public class LeaveBalanceService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    LeaveHistoryRepository leaveHistoryRepository;

    public Double calculate(Integer empId) {
        Employee employee = employeeRepository.findOne(empId);
        Double annualCount = leaveHistoryRepository.countAnnualLeave(empId);
        if (annualCount == null) {
            annualCount = 0.0;
        }
        Calendar todayCalendar = Calendar.getInstance();
        Calendar hireDateCalendar = Calendar.getInstance();
        hireDateCalendar.setTime(employee.getHireDate());
        Integer diff = todayCalendar.get(Calendar.YEAR) - hireDateCalendar.get(Calendar.YEAR);
        Integer monthDiff = diff*12+ (todayCalendar.get(Calendar.MONTH)-hireDateCalendar.get(Calendar.MONTH));
        Double all = 0.0;
        Double countLeave = leaveHistoryRepository.countByEmpId(empId);
        if (countLeave == null) {
            countLeave = 0.0;
        }
        if (monthDiff >= 24) {
            Double amount = 15.0;
            all = amount - countLeave;
        } else if (diff <= 1) {
            if (monthDiff == 12) {
                Double amount = 9.0;
                all = amount - countLeave;
            }if (monthDiff < 12) {
               if (monthDiff <3){
                    Double amount = 0.0;
                    all = amount - countLeave;
                } else
               {
                   Double amount = 3.0;
                   all = amount - countLeave;
               }
            }
        }
        Double weekday = cal2(empId);
        return all + annualCount + weekday;
    }

    public Double cal2(Integer empId) {
        List<LeaveHistory> leaveHistory = leaveHistoryRepository.findAllByEmpId(empId);
        Calendar fromCalendar = Calendar.getInstance();
        Double leave = 0.0;
        for (LeaveHistory leaves : leaveHistory) {
            BigInteger diff = leaveHistoryRepository.diffDate(leaves.getLeaveId());
            int i;
            int df = diff.intValue();
            fromCalendar.setTime(leaves.getPeriodFrom());
            Integer dayweek = fromCalendar.get(Calendar.DAY_OF_WEEK);
            if (dayweek == 1) {
                leave++;
            }
            if (dayweek == 7) {
                leave++;
            }
            for (i = 0; i < df; i++) {
                dayweek++;
                if (dayweek == 8) {
                    dayweek = 1;
                }
                if (dayweek == 1) {
                    leave++;
                }
                if (dayweek == 7) {
                    leave++;
                }
            }
        }
        return leave;
    }
}