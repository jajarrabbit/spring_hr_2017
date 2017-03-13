package com.arms.app.employee;



import com.arms.domain.entity.Employee;
import com.arms.domain.entity.EmployeeShowList;
import com.arms.domain.repository.EmployeeRepository;
import com.arms.domain.repository.EmployeeShowListRepository;
import com.arms.domain.service.EmployeeService;
import com.arms.domain.service.LeaveBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by arms20170106 on 30/1/2560.
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeShowListRepository employeeShowListRepository;

    @Autowired
    LeaveBalanceService leaveBalanceService;
    @ModelAttribute
    EmployeeDetailForm setEmployeeDetailForm(){return new EmployeeDetailForm();}
    @ModelAttribute
    EmployeeEditForm setEmployeeEditForm(){return new EmployeeEditForm();}
    @ModelAttribute
    EmployeeCreateForm setEmployeeCreateForm(){return new EmployeeCreateForm();}


    @RequestMapping(value = "emp", method = RequestMethod.GET)
    public ModelAndView blankList(ModelAndView modelAndView) {
        List<EmployeeShowList> employeeList = employeeShowListRepository.findAllLeaveLeft();
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.setViewName("/employee/emp");
        return modelAndView;
    }
    @RequestMapping(value = "emp", method = RequestMethod.POST)
    public ModelAndView showList(ModelAndView modelAndView){
        List<EmployeeShowList> employeeList = employeeShowListRepository.findAllLeaveLeft();
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.setViewName("/employee/emp");
        return modelAndView;
    }
    @RequestMapping(value = "create", params = "form", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView modelAndView) {
        List<Employee> employeeList = employeeRepository.findAll();
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.addObject("employeeCreateForm", new EmployeeCreateForm());
        modelAndView.setViewName("/employee/create");
        return modelAndView;
    }
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ModelAndView create(ModelAndView modelAndView, @Validated EmployeeCreateForm employeeCreateForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<Employee> employeeList = employeeRepository.findAll();
            modelAndView.addObject("employeeList", employeeList);
            modelAndView.setViewName("/employee/create");
            return modelAndView;
        } else {
            employeeService.save(employeeCreateForm);
            modelAndView.setViewName("redirect:/employee/emp");
            return modelAndView;
        }
    }
    @RequestMapping(value = "detail/{empId}", method = RequestMethod.GET)
    public ModelAndView showDetail(@PathVariable Integer empId, ModelAndView modelAndView) {
        modelAndView.addObject("employeeDetailForm",  employeeService.getHistoryDetailByEmpId(empId));
        modelAndView.setViewName("employee/detail");
        leaveBalanceService.calculate(empId);
        return modelAndView;
    }
    @RequestMapping(value = "edit/{empId}", method = RequestMethod.GET)
    public ModelAndView showEdit(@PathVariable Integer empId, ModelAndView modelAndView) {
        modelAndView.addObject("employeeEditForm",  employeeService.getHistoryDetailByEmpId(empId));
        modelAndView.setViewName("employee/edit");
        return modelAndView;
    }
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ModelAndView edit(ModelAndView modelAndView,EmployeeEditForm employeeEditForm) {
        employeeService.update(employeeEditForm);
        modelAndView.setViewName("redirect:/employee/emp");
        return modelAndView;
    }
}
