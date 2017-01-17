package com.arms.app.leavehistory;

import com.arms.domain.entity.Employee;
import com.arms.domain.entity.LeaveHistory;
import com.arms.domain.entity.LeaveType;
import com.arms.domain.repository.EmployeeRepository;
import com.arms.domain.repository.LeaveHistoryRepository;
import com.arms.domain.repository.LeaveTypeRepository;
import com.arms.domain.service.LeaveHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by arms20170106 on 16/1/2560.
 */
@Controller
@RequestMapping("leaveHistory")
public class LeaveHistoryController {
    @Autowired
    LeaveHistoryRepository leaveHistoryRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    LeaveHistoryService leaveHistoryService;

    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    @ModelAttribute
    LeaveHistoryForm setLeaveHistoryForm() { return new LeaveHistoryForm(); }

    @RequestMapping("list")
    public ModelAndView home4(ModelAndView modelAndView) {
        List<LeaveHistory> leaveHistoryList = leaveHistoryRepository.findAll();
        modelAndView.addObject(leaveHistoryList);
        modelAndView.setViewName("leaveHistory/list");
        return modelAndView;
    }

    @RequestMapping(value = "create", params = "form", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView modelAndView) {
        List<LeaveType> leaveTypeList = leaveTypeRepository.findAll();
        List<Employee> employeeList = employeeRepository.findAll();
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.addObject("leaveTypeList", leaveTypeList);
        modelAndView.addObject("leaveHistoryForm", new LeaveHistoryForm());
        modelAndView.setViewName("/leaveHistory/create");
        return modelAndView;
    }
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(LeaveHistoryForm leaveHistoryForm) {
        leaveHistoryService.save(leaveHistoryForm);
        return "/leaveHistory/create";
    }
}
