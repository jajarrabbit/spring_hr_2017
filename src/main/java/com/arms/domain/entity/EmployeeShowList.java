package com.arms.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by arms20170106 on 13/3/2560.
 */
@Data
@Entity
public class EmployeeShowList {
    @Id
    private Integer empId;

    private String firstName;
    private String lastName;
    private Date hireDate;
    private  Integer leaveLeft;

}
