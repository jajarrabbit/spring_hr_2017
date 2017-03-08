package com.arms.domain.repository;

import com.arms.domain.entity.AmountStart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by arms20170106 on 2/3/2560.
 */
@Repository
public interface LeaveBalanceRepository extends JpaRepository<AmountStart, Integer> {
}
