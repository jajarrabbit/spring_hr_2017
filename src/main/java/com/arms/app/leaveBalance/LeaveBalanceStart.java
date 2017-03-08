package com.arms.app.leaveBalance;

import lombok.Data;

/**
 * Created by arms20170106 on 2/3/2560.
 */
@Data
public class LeaveBalanceStart {
    private Integer id;
    private String categoryName;
    private Integer amount;
    private Integer categoryId;

    public LeaveBalanceStart(Integer id,String categoryName,Integer amount, Integer categoryId){
        this.id = id;
        this.categoryName = categoryName;
        this.amount = amount;
        this.categoryId = categoryId;
    }
    public LeaveBalanceStart(){}
}
