package com.arms.app.leavehistory;


import com.arms.app.leaveBalance.LeaveBalanceAmount;
import com.arms.app.leaveBalance.LeaveBalanceStart;
import com.arms.domain.entity.Employee;
import com.arms.domain.entity.LeaveHistory;
import com.arms.domain.entity.LeaveType;
import com.arms.domain.repository.EmployeeRepository;
import com.arms.domain.repository.LeaveHistoryRepository;
import com.arms.domain.repository.LeaveTypeRepository;
import com.arms.domain.service.LeaveBalanceService;
import com.arms.domain.service.LeaveHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    LeaveHistorySearch setLeaveHistorySearch() {return new LeaveHistorySearch();}

    @ModelAttribute
    LeaveHistoryForm setLeaveHistoryForm() { return new LeaveHistoryForm(); }

    @ModelAttribute
    LeaveHistoryDetailForm setLeaveHiistoryDetailForm(){ return new LeaveHistoryDetailForm();}

    @ModelAttribute
    LeaveHistoryEditForm setLeaveHistoryEditForm(){return new LeaveHistoryEditForm();}

    @Autowired
    LeaveBalanceService leaveBalanceService;
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView blankList(ModelAndView modelAndView) {
        List<LeaveHistory> leaveHistoryList = leaveHistoryRepository.findAll();
        List<Employee> employeeList = employeeRepository.findAll();
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.addObject("leaveHistoryList",leaveHistoryList);
        modelAndView.addObject("leaveHistorySearch", new LeaveHistorySearch());
        modelAndView.setViewName("/leaveHistory/list");
        return modelAndView;
    }
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public  ModelAndView showList(ModelAndView modelAndView, LeaveHistorySearch leaveHistorySearch) {
        List<Employee> employeeList = employeeRepository.findAll();
        modelAndView.addObject("employeeList", employeeList);
        List<LeaveHistory> leaveSearchList = leaveHistoryRepository.findAllByLeaveHistory(leaveHistorySearch.getEmpId(), leaveHistorySearch.getPeriodFrom(), leaveHistorySearch.getPeriodUntil());
        modelAndView.addObject("leaveHistoryList", leaveSearchList);
        modelAndView.setViewName("/leaveHistory/list");
        return modelAndView;
    }
    @RequestMapping(value = "detail/{leaveId}/{empId}/{categoryId}", method = RequestMethod.GET)
    public ModelAndView showDetail(@PathVariable Integer leaveId, @PathVariable Integer empId, @PathVariable Integer categoryId, ModelAndView modelAndView, LeaveBalanceAmount leaveBalanceAmount, LeaveBalanceStart leaveBalanceStart) {
        modelAndView.addObject("leaveHistoryDetailForm",  leaveHistoryService.getHistoryDetailByLeaveId(leaveId));
        leaveBalanceService.calculate(empId);
        modelAndView.setViewName("leaveHistory/detail");
        return modelAndView;
    }
    @RequestMapping(value = "edit/{leaveId}", method = RequestMethod.GET)
    public ModelAndView showEdit(@PathVariable Integer leaveId, ModelAndView modelAndView) {
        modelAndView.addObject("leaveHistoryEditForm",  leaveHistoryService.getHistoryEditByLeaveId(leaveId));
        List<LeaveType> leaveTypeList = leaveTypeRepository.findAll();
        modelAndView.addObject("leaveTypeList", leaveTypeList);
        List<Employee> employeeList = employeeRepository.findAll();
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.setViewName("leaveHistory/edit");
        return modelAndView;
    }
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ModelAndView edit(ModelAndView modelAndView,LeaveHistoryEditForm leaveHistoryEditForm) {
        leaveHistoryService.update(leaveHistoryEditForm);
        modelAndView.setViewName("redirect:/leaveHistory/list");
        return modelAndView;
    }
    @RequestMapping(value = "delete/{leaveId}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer leaveId) {
        leaveHistoryService.delete(leaveId);
        return "redirect:/leaveHistory/list";
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
    public ModelAndView create(ModelAndView modelAndView, @Validated LeaveHistoryForm leaveHistoryForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<LeaveType> leaveTypeList = leaveTypeRepository.findAll();
            List<Employee> employeeList = employeeRepository.findAll();
            modelAndView.addObject("employeeList", employeeList);
            modelAndView.addObject("leaveTypeList", leaveTypeList);
            modelAndView.setViewName("/leaveHistory/create");
            return modelAndView;
        } else {
            leaveHistoryService.save(leaveHistoryForm);
            modelAndView.setViewName("redirect:/leaveHistory/list");
            return modelAndView;
        }
    }
    @ResponseBody
    @RequestMapping(value = "getHireDate")
    Map<String, String> getHireDate(@RequestParam String empId) throws Exception {
        Map<String, String> returnMap = new LinkedHashMap<String, String>();
        returnMap = leaveHistoryService.getHireDate(empId);
        return returnMap;
    }
}
