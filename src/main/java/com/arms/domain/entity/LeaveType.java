package com.arms.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by arms20170106 on 16/1/2560.
 */
@Data
@Entity
@Table(name = "leave_type")
public class LeaveType {

    private Integer categoryId;

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    private List<LeaveHistory> leaveHistoryList;

    @OneToMany(mappedBy = "leaveType")
    public List<LeaveHistory> getLeaveHistoryList() {
        return leaveHistoryList;
    }

    public void setLeaveHistory(List<LeaveHistory> leaveHistoryList) {
        this.leaveHistoryList = leaveHistoryList;
    }

    private String categoryName;

    @Basic
    @Column(name = "category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
