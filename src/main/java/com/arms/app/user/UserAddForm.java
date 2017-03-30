package com.arms.app.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

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

    private Integer roleId;

    public UserAddForm(String username,String password,String email,Integer roleId)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
    }
    public  UserAddForm(){}
}
