package com.arms.app.leavehistory;
import lombok.Data;

@Data
public class LeaveHistoryDetailForm {
    private  Integer leaveId;
    private Integer empId;
    private String firstName;
    private  String lastName;
    private String periodFrom;
    private String periodUntil;
    private  String hireDate;
    private Integer categoryId;
    private  String categoryName;
    private String reason;
    private String remark;
    private Integer fullday;
    private Integer halfday;
    private Double  total;

    public LeaveHistoryDetailForm(Integer leaveId, Integer empId, String periodFrom, String periodUntil, Integer categoryId, String reason, String remark, String firstName,
                                  String lastName,String categoryName, String hireDate,Integer fullday,Integer halfday, Double total) {
        this.leaveId = leaveId;
        this.empId = empId;
        this.periodFrom = periodFrom;
        this.periodUntil = periodUntil;
        this.categoryId = categoryId;
        this.reason = reason;
        this.remark = remark;
        this.firstName = firstName;
        this.lastName = lastName;
        this.categoryName = categoryName;
        this.hireDate = hireDate;
        this.fullday = fullday;
        this.halfday = halfday;
        this.total = total;
    }
    public LeaveHistoryDetailForm() {}
}

