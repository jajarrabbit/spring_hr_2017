package com.arms.domain.entity;


import lombok.Data;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by arms20170106 on 13/1/2560.
 */
@Data
@Entity
@Table(name = "employees")
public class Employee {

    private Integer empId;

    @Id
    @GeneratedValue
    @Column(name = "emp_id")
    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    private List<LeaveHistory> leaveHistoryList;

    @OneToMany(mappedBy = "employee")
    public List<LeaveHistory> getLeaveHistoryList() {
        return leaveHistoryList;
    }

    public void setLeaveHistory(List<LeaveHistory> leaveHistoryList) {
        this.leaveHistoryList = leaveHistoryList;
    }

    private String firstName;

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String lastName;

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String email;

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String phone;

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private Date hireDate;

    @Basic
    @Column(name = "hire_date")
    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate() {
        this.hireDate = hireDate;
    }


}

