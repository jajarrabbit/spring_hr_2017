package com.arms.app.leavehistory;


import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
/**
 * Created by arms20170106 on 16/1/2560.
 */
@Data
public class LeaveHistoryForm {
    @NotNull
    private Integer empId;
    @NotEmpty
    private String periodFrom;
    @NotEmpty
    private String periodUntil;
    @NotNull
    private Integer categoryId;
    @NotEmpty
    private String reason;
    @NotEmpty
    private String remark;
    private String hireDate;
    private Integer fullday;
    private Integer halfday;

    public LeaveHistoryForm(Integer empId, String periodFrom, String periodUntil, Integer categoryId, String reason, String remark,String hireDate,Integer fullday,Integer halfday) {
        this.empId = empId;
        this.periodFrom = periodFrom;
        this.periodUntil = periodUntil;
        this.categoryId = categoryId;
        this.reason = reason;
        this.remark = remark;
        this.hireDate = hireDate;
        this.fullday = fullday;
        this.halfday = halfday;
    }
    public LeaveHistoryForm() {}
}
