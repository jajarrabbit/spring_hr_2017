package com.arms.domain.service;

import com.arms.app.leavehistory.LeaveHistoryForm;
import com.arms.domain.entity.LeaveHistory;
import com.arms.domain.repository.LeaveHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by arms20170106 on 16/1/2560.
 */
@Service
@Transactional
public class LeaveHistoryService {

    @Autowired
    LeaveHistoryRepository leaveHistoryRepository;

    public void save(LeaveHistoryForm leaveHistoryForm) {
        LeaveHistory leaveHistory = new LeaveHistory();
        leaveHistory.setEmpId(leaveHistoryForm.getEmpId());
        leaveHistory.setPeriodFrom(leaveHistoryForm.getPeriodFrom());
        leaveHistory.setPeriodUntil(leaveHistoryForm.getPeriodUntil());
        leaveHistory.setCategoryId(leaveHistoryForm.getCategoryId());
        leaveHistory.setReason(leaveHistoryForm.getReason());
        leaveHistory.setRemark(leaveHistoryForm.getRemark());
        leaveHistoryRepository.save(leaveHistory);
    }
}
