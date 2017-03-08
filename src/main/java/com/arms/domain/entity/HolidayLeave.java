package com.arms.domain.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by arms20170106 on 20/2/2560.
 */
@Data
@Entity
@Table(name = "holiday_leave")
public class HolidayLeave {
    private Integer holId;
    @Id
    @GeneratedValue
    @Column(name = "hol_id")
    public Integer getHolId(){return holId;}public void setHolId(Integer holId){this.holId=holId;}
    private Date holidayDate;
    @Basic
    @Column(name = "holiday_date")
    public Date setHolidayDate(){return holidayDate;}public void  setHolidayDate(Date holidayDate){this.holidayDate=holidayDate;}
    private String holidayDetail;
    @Basic
    @Column(name = "holiday_detail")
    public String setHolidayDetail(){return holidayDetail;}public void setHolidayDetail(String holidayDetail){this.holidayDetail=holidayDetail;}


}
