package com.arms.app.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * Created by arms20170106 on 7/2/2560.
 */
@Data
public class UserAddForm {
    @NotEmpty
    private String username;

    @NotEmpty
    private  String password;

    @NotEmpty
    private  String email;
}
