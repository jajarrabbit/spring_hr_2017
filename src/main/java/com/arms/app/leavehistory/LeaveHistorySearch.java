package com.arms.app.leavehistory;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by arms20170106 on 19/1/2560.
 */
@Data
public class LeaveHistorySearch {
        private Integer empId;
        private String periodFrom;
        private String periodUntil;
        private Integer categoryId;
        private String reason;
        private String remark;

        public LeaveHistorySearch(Integer empId, String periodFrom, String periodUntil, Integer categoryId, String reason, String remark) {
            this.empId = empId;
            this.periodFrom = periodFrom;
            this.periodUntil = periodUntil;
            this.categoryId = categoryId;
            this.reason = reason;
            this.remark = remark;
        }
        public LeaveHistorySearch() {}
    }
