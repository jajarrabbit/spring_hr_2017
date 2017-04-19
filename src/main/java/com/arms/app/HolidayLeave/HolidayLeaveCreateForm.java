package com.arms.app.HolidayLeave;


import lombok.Data;

import java.util.Date;

/**
 * Created by arms20170106 on 24/2/2560.
 */
@Data
public class HolidayLeaveCreateForm {
    private Integer holId;
    private Date holidayDate;
    private String holidayDetail;

    public HolidayLeaveCreateForm(Integer holId, Date holidayDate, String holidayDetail) {
        this.holId = holId;
        this.holidayDate = holidayDate;
        this.holidayDetail = holidayDetail;
    }

    public HolidayLeaveCreateForm() {
    }

}
