package com.arms.domain.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

/**
 * Created by arms20170106 on 13/3/2560.
 */
@Data
@Entity
@Table(name = "comp_detail")
public class CompDetail {

    private  Integer compDetailId;
    @Id
    @GeneratedValue
    @Column(name = "comp_detail_id")
    public Integer getCompDetailId(){return compDetailId;}
    public  void setCompDetailId(Integer compDetailId){this.compDetailId = compDetailId;}

    private Date compAddDate;
    @Basic
    @Column(name = "comp_add_date")
    public Date getCompAddDate(){return compAddDate;}
    public  void setCompAddDate(Date compAddDate){this.compAddDate = compAddDate;}

    private String compDetail;
    @Basic
    @Column(name = "comp_detail")
    public String getCompDetail(){return compDetail;}
    public  void setCompDetail(String compDetail){this.compDetail = compDetail;}

    private Integer empId;
    @Basic
    @Column(name = "emp_id")
    public Integer getEmpId(){return empId;}
    public void setEmpId(Integer empId){this.empId = empId;}

//    private List<LeaveHistory> leaveHistoryLists;
//    @OneToMany(mappedBy = "compDetail")
//    public List<LeaveHistory> getLeaveHistoryLists(){
//        return leaveHistoryLists;
//    }
//    public void setLeaveHistorys(List<LeaveHistory> leaveHistoryList){
//        this.leaveHistoryLists = leaveHistoryList;
//    }

    private Integer isUsed;
    @Basic
    @Column(name = "is_used")
    public Integer getIsUsed(){return isUsed;}
    public  void  setIsUsed(Integer isUsed){this.isUsed = isUsed;}

}
