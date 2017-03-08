package com.arms.domain.repository;

import com.arms.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by arms20170106 on 13/1/2560.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
