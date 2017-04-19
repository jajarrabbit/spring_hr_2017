package com.arms.app.compensationday;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * Created by arms20170106 on 13/3/2560.
 */
@Data
public class CompCreateForm {
    private Integer compDetailId;
    private Integer empId;
    private String compDetail;
    private Date compAddDate;
    private Integer isused;

    public CompCreateForm(Integer compDetailId, Integer empId, String compDetail, Date compAddDate, Integer isused) {
        this.compDetailId = compDetailId;
        this.compDetail = compDetail;
        this.compAddDate = compAddDate;
        this.empId = empId;
        this.isused = isused;
    }

    public CompCreateForm() {
    }
}
