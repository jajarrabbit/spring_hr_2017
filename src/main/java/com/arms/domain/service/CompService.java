package com.arms.domain.service;

import com.arms.app.compensationday.CompCreateForm;
import com.arms.domain.entity.CompDetail;
import com.arms.domain.entity.Employee;
import com.arms.domain.repository.CompDetailRepository;
import com.arms.domain.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by arms20170106 on 14/3/2560.
 */
@Service
public class CompService {
    @Autowired
    CompDetailRepository compDetailRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public void save(CompCreateForm compCreateForm) {
        CompDetail compDetail = new CompDetail();
        compDetail.setEmpId(compCreateForm.getEmpId());
        compDetail.setIsUsed(0);
        compDetail.setCompAddDate(compCreateForm.getCompAddDate());
        compDetail.setCompDetail(compCreateForm.getCompDetail());
        compDetailRepository.save(compDetail);
    }

    public void expired() {
        List<CompDetail> compDetailList = compDetailRepository.findAll();

        for (CompDetail compDetailItem : compDetailList) {
            Calendar todayCalendar = Calendar.getInstance();
            Calendar AddDateCalendar = Calendar.getInstance();
            AddDateCalendar.setTime(compDetailItem.getCompAddDate());
            if (todayCalendar.get(Calendar.YEAR) - AddDateCalendar.get(Calendar.YEAR) >= 1) {
                compDetailItem.setIsUsed(1);
                compDetailRepository.save(compDetailItem);
            }
            Integer diff = todayCalendar.get(Calendar.MONTH) - AddDateCalendar.get(Calendar.MONTH);
            if (diff >= 2) {
                compDetailItem.setIsUsed(1);
                compDetailRepository.save(compDetailItem);
            }
        }
    }

    public int compCal(Integer empId) {
        int count = compDetailRepository.countCompByEmpId(empId);
        return count;
    }
}
