package com.arms.app.leavehistory;

/**
 * Created by arms20170106 on 20/1/2560.
 */

import lombok.Data;
/**
 * Created by arms20170106 on 19/1/2560.
 */
@Data
public class LeaveHistoryDetailForm {

    private  Integer leaveId;
    private Integer empId;
    private String firstName;
    private  String lastName;
    private String periodFrom;
    private String periodUntil;
    private  String hireDate;
    private Integer categoryId;
    private  String categoryName;
    private String reason;
    private String remark;


    public LeaveHistoryDetailForm(Integer leaveId, Integer empId, String periodFrom, String periodUntil, Integer categoryId, String reason, String remark, String firstName,
                                  String lastName,String categoryName, String hireDate) {
        this.leaveId = leaveId;
        this.empId = empId;
        this.periodFrom = periodFrom;
        this.periodUntil = periodUntil;
        this.categoryId = categoryId;
        this.reason = reason;
        this.remark = remark;
        this.firstName = firstName;
        this.lastName = lastName;
        this.categoryName = categoryName;
        this.hireDate = hireDate;

    }
    public LeaveHistoryDetailForm() {}


}

