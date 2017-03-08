package com.arms.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by arms20170106 on 2/3/2560.
 */
@Data
@Entity
@Table(name = "amount_start")
public class AmountStart {
    private Integer id;
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId(){return id;}
    public void setId(Integer id){this.id = id;}
    private String categoryName;
    @Basic
    @Column(name = "category_name")
    public String getCategoryName(){return categoryName;}
    public void setCategoryName(String categoryName){this.categoryName = categoryName;}
    private Integer amount;
    @Basic
    @Column(name = "amount")
    public Integer getAmount(){return amount;}
    public void setAmount(Integer amount){this.amount = amount;}
    private Integer categoryId;
    @Basic
    @Column(name = "category_id")
    public Integer getCategoryId(){return categoryId;}
    public void setCategoryId(Integer categoryId){this.categoryId = categoryId;}

//    private LeaveType leaveType;
//    @ManyToOne
//    @JoinColumn(name = "category_id" , insertable = false , updatable = false)
//    public LeaveType getLeaveType() {return leaveType;}
//    public void setLeaveType(LeaveType leaveType) {this.leaveType = leaveType;}
}
