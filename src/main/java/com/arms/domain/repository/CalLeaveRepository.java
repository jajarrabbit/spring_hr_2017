package com.arms.domain.repository;

import com.arms.domain.entity.CalLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by arms20170106 on 10/3/2560.
 */
@Repository
public interface CalLeaveRepository extends JpaRepository <CalLeave,Integer> {
}
