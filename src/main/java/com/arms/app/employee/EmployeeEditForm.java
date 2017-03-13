package com.arms.app.employee;

import lombok.Data;

/**
 * Created by arms20170106 on 31/1/2560.
 */
@Data
public class EmployeeEditForm {
    private Integer empId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String hireDate;
    private Integer leaveLeft;

    public  EmployeeEditForm (Integer leaveLeft,Integer empId, String firstName, String lastName, String email, String phone, String hireDate){
        this.empId =empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
        this.leaveLeft = leaveLeft;
    }
    public  EmployeeEditForm() {}
}
