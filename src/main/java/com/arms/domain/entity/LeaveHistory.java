package com.arms.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.StringReader;
import java.util.Date;

/**
 * Created by arms20170106 on 13/1/2560.
 */
@Data
@Entity
@Table(name = "leave_history")
public class LeaveHistory {

    private Integer leaveId;

    @Id
    @GeneratedValue
    @Column(name = "leave_id")
    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    private Integer empId;

    @Basic
    @Column(name = "emp_id")
    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "emp_id", insertable = false, updatable = false)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private Date periodFrom;

    @Basic
    @Column(name = "period_from")
    public Date getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(Date periodFrom) {
        this.periodFrom = periodFrom;
    }

    private Date periodUntil;

    @Basic
    @Column(name = "period_until")
    public Date getPeriodUntil() {
        return periodUntil;
    }

    public void setPeriodUntil(Date periodUntil) {
        this.periodUntil = periodUntil;
    }

    private Integer categoryId;

    @Basic
    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    private LeaveType leaveType;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    private String reason;

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    private String remark;

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private Integer fullday;

    @Basic
    @Column(name = "fullday")
    public Integer getFullday() {
        return fullday;
    }

    public void setFullday(Integer fullday) {
        this.fullday = fullday;
    }

    private Integer halfday;

    @Basic
    @Column(name = "halfday")
    public Integer getHalfday() {
        return halfday;
    }

    public void setHalfday(Integer halfday) {
        this.halfday = halfday;
    }

    private Integer approve;
    @Basic
    @Column(name = "approve")
    public Integer getApprove(){return approve;}
    public  void setApprove(Integer approve) {this.approve = approve;}

}