package com.arms.app.leavehistory;


import lombok.Data;

import java.util.Date;


/**
 * Created by arms20170106 on 16/1/2560.
 */
@Data
public class LeaveHistoryForm {
    private Integer empId;
    private Date periodFrom;
    private Date periodUntil;
    private Integer categoryId;
    private String reason;
    private String remark;
    private String hireDate;

    public LeaveHistoryForm(Integer empId, Date periodFrom, Date periodUntil, Integer categoryId, String reason, String remark) {
        this.empId = empId;
        this.periodFrom = periodFrom;
        this.periodUntil = periodUntil;
        this.categoryId = categoryId;
        this.reason = reason;
        this.remark = remark;
        this.hireDate = hireDate;
    }
    public LeaveHistoryForm() {}
}
