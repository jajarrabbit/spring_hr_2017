package com.arms.app.employee;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by arms20170106 on 31/1/2560.
 */
@Data
public class EmployeeEditForm {
    private Integer empId;
    private String firstName;
    private String lastName;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    private String email;
    @NotEmpty
    @Pattern(regexp = "0[8-9][0-9]*")
    @Size(min = 9, max = 10)
    private String phone;
    private String hireDate;
    private Integer leaveLeft;

    public EmployeeEditForm(Integer leaveLeft, Integer empId, String firstName, String lastName, String email, String phone, String hireDate) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
        this.leaveLeft = leaveLeft;
    }

    public EmployeeEditForm() {
    }
}
