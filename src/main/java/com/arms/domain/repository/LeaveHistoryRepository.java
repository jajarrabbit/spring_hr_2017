package com.arms.domain.repository;

import com.arms.domain.entity.LeaveHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Created by arms20170106 on 13/1/2560.
 */
public interface LeaveHistoryRepository extends JpaRepository<LeaveHistory, Integer> {

//    List<LeaveHistory> findAllByEmpId(Integer empId);

    @Query(value = " SELECT * FROM leave_history " +
            " WHERE " +
            "CASE WHEN :empId IS NULL THEN " +
            " TRUE" +
            " ELSE" +
            " emp_id = :empId  " +
            " END " +
            " AND CASE WHEN :periodFrom IS NULL OR :periodFrom = '' THEN " +
            " TRUE " +
            " ELSE " +
            " period_from >= :periodFrom " +
            " END " +
            " AND CASE WHEN :periodUntil IS NULL OR :periodUntil = '' THEN " +
            " TRUE " +
            " ELSE " +
            " period_until <= :periodUntil " +
            " END "
             , nativeQuery = true)
    List<LeaveHistory> findAllByLeaveHistory(@Param("empId") Integer empId,@Param("periodFrom") String periodFrom,@Param("periodUntil") String periodUntil);
}
