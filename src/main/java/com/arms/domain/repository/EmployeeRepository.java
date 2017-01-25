package com.arms.domain.repository;

import com.arms.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by arms20170106 on 13/1/2560.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
