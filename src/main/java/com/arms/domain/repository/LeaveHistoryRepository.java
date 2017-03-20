package com.arms.domain.repository;

import com.arms.domain.entity.LeaveHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by arms20170106 on 13/1/2560.
 */
@Repository
public interface LeaveHistoryRepository extends JpaRepository<LeaveHistory, Integer> {
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

    @Query(value = " SELECT SUM( fullday + ( halfday / 2 ) ) AS counts FROM leave_history " +
            " WHERE " +
            " emp_id = :empId AND category_id = 1 AND year( period_from )  BETWEEN year( now() ) - 5 AND year( now() ) "
            , nativeQuery = true)
    Double countByEmpId(@Param("empId") Integer empId);

    @Query(value = "SELECT COUNT(*) AS counts  FROM leave_history " +
            " INNER JOIN holiday_leave " +
            " ON holiday_leave.holiday_date >= leave_history.period_from and holiday_leave.holiday_date <= leave_history.period_until " +
            " WHERE   emp_id = :empId AND category_id =1 AND year( period_from )  BETWEEN year( now() ) - 5 AND year( now() )"
            , nativeQuery = true)
    Double countAnnualLeave(@Param("empId") Integer empId);

    @Query(value = " SELECT datediff( period_until , period_from ) + 1 AS count FROM leave_history " +
            " WHERE leave_id = :leaveId AND category_id = 1 "
            ,nativeQuery = true)
    BigInteger diffDate(@Param("leaveId") Integer leaveId);

    @Query(value = " SELECT * FROM leave_history" +
            " WHERE emp_Id = :empId AND category_id = 1 "
            ,nativeQuery = true)
    List<LeaveHistory> findAllByEmpId(@Param("empId")Integer empId);

    @Query(value = "SELECT MAX(leave_id) FROM leave_history "
             , nativeQuery = true)
    Integer findMaxLeaveId();
}