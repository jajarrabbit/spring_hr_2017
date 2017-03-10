package com.arms.domain.service;

import com.arms.domain.entity.*;
import com.arms.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by arms20170106 on 2/3/2560.
 */
@Service
public class LeaveBalanceService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    LeaveBalanceRepository leaveBalanceRepository;;
//    @Autowired
//    CalLeaveRepository calLeaveRepository;
    @Autowired
    LeaveHistoryRepository leaveHistoryRepository;

//    public void balance(LeaveBalanceAmount leaveBalanceAmount, Integer empId, LeaveBalanceStart leaveBalanceStart, Integer categoryId)
//{
//    LeaveBalanceAmount leaveAmount = new LeaveBalanceAmount();
//    Employee employee = employeeRepository.findOne(empId);
//    LeaveHistory leaveHistory = leaveHistoryRepository.findOne(empId);
//    CalLeave calLeave = new CalLeave();
//    AmountStart amountStart = leaveBalanceRepository.findOne(categoryId);
//    Calendar calendar = Calendar.getInstance();
//    Date a = employee.getHireDate() ;
//    long diff = calendar.getTime().getYear() - a.getYear();
//    int i;
//    if (amountStart.getCategoryId() ==1) {
//        int am = amountStart.getAmount();
//        for (i = 0; i <= diff; i++) {
//            if (diff <= 3) {
//                 am = am+1;
//            } else if ( diff > 3)
//            {
//                am = am + 3;
//                if(am >=15){
//                    am = 15;
//                }
//            }
//        }
//        calLeave.setBLeaveAmount(am);
//    }else  if (amountStart.getCategoryId() == 3){
//        int am = amountStart.getAmount();
//        for (i =0; i <= diff; i++){
//            if (diff <=3){
//                am = am +1;
//            } else if (diff > 3){
//                am = am + 3;
//                if (am >= 15){
//                    am = 15;
//                }
//            }
//        }
//        calLeave.setSiceLeaveAmount(am);
//    }
//    calLeave.setEmpId(leaveBalanceAmount.getEmpId());
//
//    calLeaveRepository.save(calLeave);
//}
    public void calculate(Integer empId) {
        Employee employee = employeeRepository.findOne(empId);
        Calendar calendar = Calendar.getInstance();
        Date a = employee.getHireDate();
        Integer diff = calendar.getTime().getYear() - a.getYear();
        int all =0;
        if (diff > 6)
        {
            int amount = 15;
            Integer countLeave = leaveHistoryRepository.countByEmpId(empId);
              all  = amount - countLeave;

        }
        if (diff <= 5)
        {

            if (diff == 5)
            {
                int amount = 12;
                Integer countLeave = leaveHistoryRepository.countByEmpId(empId);
                 all  = amount - countLeave;
            }
            if (diff == 4)
            {
                int amount = 9;
                Integer countLeave = leaveHistoryRepository.countByEmpId(empId);
                 all  = amount - countLeave;
            }
            if (diff == 3)
            {
                int amount = 6;
                Integer countLeave = leaveHistoryRepository.countByEmpId(empId);
                 all  = amount - countLeave;
            }
            if (diff == 2)
            {
                int amount = 5;
                Integer countLeave = leaveHistoryRepository.countByEmpId(empId);
                 all  = amount - countLeave;
            }
            if (diff == 1)
            {
                int amount = 4;
                Integer countLeave = leaveHistoryRepository.countByEmpId(empId);
                 all  = amount - countLeave;
            }
        }
      //***save status for all
    }
}
