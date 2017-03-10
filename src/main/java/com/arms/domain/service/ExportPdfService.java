package com.arms.domain.service;


import com.arms.domain.bean.JasperPdfModelBean;
import com.arms.domain.bean.PdfBean;
import com.arms.domain.entity.Employee;
import com.arms.domain.entity.LeaveHistory;
import com.arms.domain.entity.LeaveType;
import com.arms.domain.repository.EmployeeRepository;
import com.arms.domain.repository.LeaveHistoryRepository;
import com.arms.domain.repository.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arms20170106 on 6/3/2560.
 */
@Service
public class ExportPdfService {
    @Autowired
    LeaveHistoryRepository leaveHistoryRepository;
    @Value("${leave.jasper.path}")
    private String jasperPath;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    public JasperPdfModelBean getJasperPdfView(Integer leaveId) {
        LeaveHistory leaveHistory = leaveHistoryRepository.findOne(leaveId);
        List<PdfBean> beanList = new ArrayList<>();
        setAllLeaveHistory(leaveHistory, beanList, leaveId);
String filename = "leave notification";

return new JasperPdfModelBean(jasperPath +"/leave/leaveCer.jrxml",beanList,filename,jasperPath);
    }

    private void setAllLeaveHistory(LeaveHistory leaveHistory, List<PdfBean> beanList, Integer leaveId)
    {
         PdfBean bean = new PdfBean();
         if(leaveHistory.getEmpId() != null)
         {
             Employee employee = employeeRepository.findOne(leaveHistory.getEmpId());
             bean.setFirstName(employee.getFirstName());
             bean.setLastName(employee.getLastName());
         }
         if (leaveHistory.getPeriodFrom() != null)
        {
            bean.setPeriodFrom(leaveHistory.getPeriodFrom());
        }
        if (leaveHistory.getPeriodUntil() != null)
        {
            bean.setPeriodUntil(leaveHistory.getPeriodUntil());
        }
        if (leaveHistory.getCategoryId()!= null)
        {
            LeaveType leaveType = leaveTypeRepository.findOne(leaveHistory.getCategoryId());
            bean.setCategoryName(leaveType.getCategoryName());
        }
        if (leaveHistory.getReason() != null)
        {
            bean.setReason(leaveHistory.getReason());
        }
        if(leaveHistory.getRemark() != null)
        {
            bean.setRemark(leaveHistory.getRemark());
        }
    }
}
