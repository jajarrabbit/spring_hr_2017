package com.arms.domain.service;

import com.arms.app.HolidayLeave.HolidayLeaveCreateForm;
import com.arms.domain.entity.HolidayLeave;
import com.arms.domain.repository.HolidayLeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by arms20170106 on 24/2/2560.
 */
@Service
public class HolidayLeaveService {
    @Autowired
    HolidayLeaveRepository holidayLeaveRepository;

    public void save(HolidayLeaveCreateForm holidayLeaveCreateForm) {
        HolidayLeave holidayLeave = new HolidayLeave();
        holidayLeave.setHolidayDate(holidayLeaveCreateForm.getHolidayDate());
        holidayLeave.setHolidayDetail(holidayLeaveCreateForm.getHolidayDetail());
        holidayLeaveRepository.save(holidayLeave);
    }

    public void delete(Integer holId) {
        holidayLeaveRepository.delete(holId);
    }
}
