package com.arms.domain.repository;

import com.arms.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by arms20170106 on 13/1/2560.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findOneByFirstName(String firstName);


    @Query(value = "SELECT * FROM employees WHERE first_name = :xxx", nativeQuery = true)
    Employee findPerson(@Param("xxx") String firstName);
}
