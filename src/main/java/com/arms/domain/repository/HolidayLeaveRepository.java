package com.arms.domain.repository;

import com.arms.domain.entity.HolidayLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by arms20170106 on 20/2/2560.
 */
@Repository
public interface HolidayLeaveRepository extends JpaRepository<HolidayLeave, Integer> {

    @Query(value = "SELECT * FROM holiday_leave WHERE YEAR(holiday_date) = :year"
            , nativeQuery = true)
    List<HolidayLeave> findAllByHolidayDate(@Param("year") String year);



}
