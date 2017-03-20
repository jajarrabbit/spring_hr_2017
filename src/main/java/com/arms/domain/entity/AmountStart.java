package com.arms.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by arms20170106 on 8/3/2560.
 */
@Data
@Entity
@Table(name = "amount_start")
public class AmountStart {
   private Integer id;
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {return id;}
    public  void  setId(Integer Id) {this.id = id;}

    private String categoryName;
    @Column
    @Basic
    public String getCategoryName() {return  categoryName;}
   public void  setCategoryName(String categoryName ){this.categoryName = categoryName;}

    private String amount;
    @Column
    @Basic
    public String getAmount() {return  amount;}
    public void  setAmount(String amount){this.amount = amount;}

    private Integer categoryId;
    @Column
    @Basic
    public Integer getCategoryId() {return  categoryId;}
    public void  setCategoryId(Integer categoryId){this.categoryId = categoryId;}

}
