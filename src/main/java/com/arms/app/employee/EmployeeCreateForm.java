package com.arms.app.employee;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by arms20170106 on 30/1/2560.
 */
@Data
public class EmployeeCreateForm {

    private Integer empId;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String hireDate;

    public  EmployeeCreateForm (Integer empId, String firstName, String lastName, String email, String phone, String hireDate){
        this.empId =empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
    }
    public  EmployeeCreateForm() {}
}
