package com.arms.app.calLeave;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by arms20170106 on 10/3/2560.
 */
@Data
public class CalLeaveForm {
    private Integer id;
    private  Integer empId;
    private  Integer bLeave;

    public CalLeaveForm(Integer id,Integer empId,Integer bLeave)
    {
        this.id = id;
        this.empId = empId;
        this.bLeave = bLeave;
    }
    public  CalLeaveForm(){}
}
