package com.arms.domain.repository;

import com.arms.domain.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by arms20170106 on 17/1/2560.
 */
@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {
}
