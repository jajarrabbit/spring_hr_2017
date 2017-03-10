package com.arms.domain.bean;//package com.arms.domain.bean;

import lombok.Data;

import java.util.Date;

/**
 * Created by arms20170106 on 6/3/2560.
 */
@Data
public class PdfBean {

    private String firstName;
    private String lastName;
    private Date periodFrom;
    private Date periodUntil;
    private String categoryName;
    private String remark;
    private String reason;
}
