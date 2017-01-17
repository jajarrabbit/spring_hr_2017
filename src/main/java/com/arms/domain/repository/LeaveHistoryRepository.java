package com.arms.domain.repository;

import com.arms.domain.entity.LeaveHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by arms20170106 on 13/1/2560.
 */
public interface LeaveHistoryRepository extends JpaRepository<LeaveHistory, Integer> {
}
