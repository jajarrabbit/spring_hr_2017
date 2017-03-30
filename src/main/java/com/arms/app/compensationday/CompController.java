package com.arms.app.compensationday;



import com.arms.domain.entity.Employee;
import com.arms.domain.repository.EmployeeRepository;
import com.arms.domain.service.CompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by arms20170106 on 13/3/2560.
 */
@Controller
@RequestMapping("compensation")
public class CompController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CompService compService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "add", params = "form", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView modelAndView) {
        List<Employee> employeeList = employeeRepository.findAll();
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.addObject("compCreateForm",new CompCreateForm());
        modelAndView.setViewName("/compensation/add");
        return modelAndView;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView create(ModelAndView modelAndView, @Validated CompCreateForm compCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<Employee> employeeList = employeeRepository.findAll();
            modelAndView.addObject("employeeList", employeeList);
            modelAndView.setViewName("/compensation/add");
            return modelAndView;
        } else {
            List<Employee> employeeList = employeeRepository.findAll();
            modelAndView.addObject("employeeList", employeeList);
            compService.save(compCreateForm);
            modelAndView.setViewName("/employee/emp");
            return modelAndView;
        }
    }
}
