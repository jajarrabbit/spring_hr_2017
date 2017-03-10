package com.arms.domain.service;
import com.arms.app.employee.EmployeeCreateForm;
import com.arms.app.employee.EmployeeDetailForm;
import com.arms.app.employee.EmployeeEditForm;
import com.arms.app.leavehistory.LeaveHistoryEditForm;
import com.arms.domain.entity.Employee;
import com.arms.domain.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Created by arms20170106 on 30/1/2560.
 */@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public void save(EmployeeCreateForm employeeCreateForm) {
        Employee employee = new Employee();
        SimpleDateFormat formatt = new SimpleDateFormat("yyyy/MM/dd");
        employee.setFirstName(employeeCreateForm.getFirstName());
        employee.setLastName(employeeCreateForm.getLastName());
        employee.setEmail(employeeCreateForm.getEmail());
        employee.setPhone(employeeCreateForm.getPhone());
        try{
            employee.setHireDate(formatt.parse(employeeCreateForm.getHireDate()));
        }catch(ParseException ex){}
        employeeRepository.save(employee);
    }

    public EmployeeDetailForm getHistoryDetailByEmpId (Integer empId) {
        EmployeeDetailForm view = new EmployeeDetailForm();
        Employee emp = employeeRepository.findOne(empId);
        view.setEmpId(emp.getEmpId());
        view.setFirstName(emp.getFirstName());
        view.setLastName(emp.getLastName());
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        view.setHireDate(df.format(emp.getHireDate()));
        view.setEmail(emp.getEmail());
        view.setPhone(emp.getPhone());
        return view;
    }

    public void update(EmployeeEditForm employeeEditForm) {
        Employee employee = employeeRepository.findOne(employeeEditForm.getEmpId());
        employee.setFirstName(employeeEditForm.getFirstName());
        employee.setLastName(employeeEditForm.getLastName());
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        try{
            employee.setHireDate(df.parse(employeeEditForm.getHireDate()));
        }catch(ParseException ex){}
        employee.setEmail(employeeEditForm.getEmail());
        employee.setPhone(employeeEditForm.getPhone());
        employeeRepository.save(employee);
    }
}