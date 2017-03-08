package com.arms.app.leavehistory;

import lombok.Data;
/**
 * Created by arms20170106 on 19/1/2560.
 */
@Data
public class LeaveHistoryEditForm {

    private  Integer leaveId;
    private Integer empId;
    private String periodFrom;
    private String periodUntil;
    private  String hireDate;
    private Integer categoryId;
    private String reason;
    private String remark;
    private String firstName;
    private String lastName;

    public LeaveHistoryEditForm(Integer leaveId, Integer empId, String periodFrom, String periodUntil, Integer categoryId, String reason, String remark, String hireDate,String firstName,String lastName) {
        this.leaveId = leaveId;
        this.empId = empId;
        this.periodFrom = periodFrom;
        this.periodUntil = periodUntil;
        this.categoryId = categoryId;
        this.reason = reason;
        this.remark = remark;
        this.hireDate = hireDate;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public LeaveHistoryEditForm() {}



}

