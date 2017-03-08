package com.arms.app.leaveBalance;

import lombok.Data;

/**
 * Created by arms20170106 on 2/3/2560.
 */
@Data
public class LeaveBalanceAmount {

    private Integer calId;
    private Integer empId;
    private Integer bLeaveAmount;
    private Integer sickLeaveAmount;

    public LeaveBalanceAmount(Integer calId,Integer empId,Integer bLeaveAmount,Integer sickLeaveAmount)
    {
        this.calId = calId;
        this.empId = empId;
        this.bLeaveAmount = bLeaveAmount;
        this.sickLeaveAmount = sickLeaveAmount;
    }
    public LeaveBalanceAmount(){}
}
