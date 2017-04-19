package com.arms.app.HolidayLeave;

import lombok.Data;

import java.util.Date;

/**
 * Created by arms20170106 on 22/2/2560.
 */
@Data
public class AnnualLeaveSearch {
    private Integer holId;
    private String year;
    private Date holidayDate;
    private String holidayDetail;

    public AnnualLeaveSearch(Integer holId, String year, Date holidayDate, String holidayDetail) {
        this.holId = holId;
        this.year = year;
        this.holidayDate = holidayDate;
        this.holidayDetail = holidayDetail;
    }

    public AnnualLeaveSearch() {
    }
}
