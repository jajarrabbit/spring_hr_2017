package com.arms.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by arms20170106 on 28/3/2560.
 */
@Data
@Entity
@Table(name = "role")
public class Role {
    private int id;
    private Integer roleId;
    private String roleName;
    private List<User> userList;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    @Basic
    @Column(name = "role_id")
    public Integer getRoleId(){return roleId;}
    public  void setRoleId(Integer roleId){this.roleId = roleId;}

    @Basic
    @Column(name = "role_name")
    public String getRoleName(){return roleName;}
    public void getRoleName(String roleName){this.roleName = roleName;}

    @OneToMany(mappedBy = "role")
    public List<User> getUserList(){return userList;}
    public void setUserList(List<User> userList){this.userList = userList;}
}
