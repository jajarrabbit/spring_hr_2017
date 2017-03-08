package com.arms.domain.entity;

import lombok.Data;

import javax.persistence.*;



/**
 * Created by arms20170106 on 2/3/2560.
 */

@Data
@Entity
@Table(name = "cal_leave")
public class CalLeave {
private Integer calId;
@Id
@GeneratedValue
@Column(name = "cal_id")
public Integer getCalId(){return calId;}
public  void setCalId(Integer calId){this.calId = calId;}
private Integer empId;
@Basic
@Column(name = "emp_id")
public Integer getEmpId(){return empId;}
public void setEmpId(Integer empId){this.empId = empId;}
private Integer bLeaveAmount;
@Basic
@Column(name = "b_leave_amount")
public Integer getBLeaveAmount(){return bLeaveAmount;}
public void setBLeaveAmount(Integer bLeaveAmount){this.bLeaveAmount = bLeaveAmount;}
private Integer sickLeaveAmount;
@Basic
@Column(name = "sick_leave_amount")
public Integer getSickLeaveAmount(){return sickLeaveAmount;}
public void setSiceLeaveAmount(Integer sickLeaveAmount){this.sickLeaveAmount = sickLeaveAmount;}
}
