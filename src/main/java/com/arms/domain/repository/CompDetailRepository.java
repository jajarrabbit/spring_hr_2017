package com.arms.domain.repository;

import com.arms.domain.entity.CompDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by arms20170106 on 13/3/2560.
 */
@Repository
public interface CompDetailRepository extends JpaRepository<CompDetail,Integer> {

    @Query(value = " SELECT COUNT(*) AS counts FROM comp_detail " +
            " WHERE " +
            " emp_id = :empId AND is_used = 0  "
            , nativeQuery = true)
    Integer countCompByEmpId(@Param("empId") Integer empId);

    @Query(value = " SELECT MIN( comp_add_date ) FROM comp_detail " +
            " WHERE " +
            " emp_id = :empId AND is_used = 0 "
            , nativeQuery = true)
    Date CompByEmpId(@Param("empId") Integer empId);
}
