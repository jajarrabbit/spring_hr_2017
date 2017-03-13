package com.arms.domain.repository;

import com.arms.domain.entity.EmployeeShowList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by arms20170106 on 13/3/2560.
 */
@Repository
public interface EmployeeShowListRepository extends JpaRepository<EmployeeShowList,Integer> {
    @Query(value = " SELECT employees.emp_id , employees.first_name " +
            " , employees.last_name , employees.email , employees.phone " +
            " , employees.hire_date , cal_leave.b_leave_amount AS leave_left " +
            " FROM employees " +
            " LEFT JOIN cal_leave " +
            " ON employees.emp_id = cal_leave.emp_id "
            , nativeQuery = true )
    List<EmployeeShowList> findAllLeaveLeft();
}
