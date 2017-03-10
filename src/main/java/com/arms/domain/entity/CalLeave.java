package com.arms.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cal_leave")
public class CalLeave {

    private Integer calId;
    @Id
    @GeneratedValue
    @Column(name = "cal_id")
    public Integer getCalId(){return calId;}
    public void setCalId(Integer calId){
        this.calId = calId;
    }



    public Integer empId;
    @Basic
    @Column(name = "emp_id")
    public Integer getEmpId(){
        return empId;
    }
    public void setEmpId(Integer empId){
        this.empId = empId;
    }




    private Integer bLeave;
    @Basic
    @Column(name = "b_leave_amount")
    public Integer getBLeave(){
        return bLeave;
    }
    public void setBLeave(Integer bLeave){
        this.bLeave = bLeave;
    }
}
