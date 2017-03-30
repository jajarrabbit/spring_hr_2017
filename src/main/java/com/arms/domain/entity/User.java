package com.arms.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by arms20170106 on 7/2/2560.
 */
@Entity
@Data
@Table(name = "user")
public class User {
private Integer id;
private  String username;
private String password;
private String email;
private Date created;
private Integer roleId;

@Id
@GeneratedValue
@Column(name = "id")
public Integer getId(){return id;} public void setId(Integer id){this.id = id;}

@Basic
@Column(name = "username")
public  String getUsername() {return username;} public void  setUsername(String username){this.username = username;}

@Basic
@Column(name = "password")
public String getPassword() {return  password;} public void setPassword(String password){this.password =password;}

@Basic
@Column(name = "email")
public  String getEmail() {return email;} public  void  setEmail(String email){this.email = email;}

@Basic
@Column(name = "created")
public  Date getCreated() {return  created;} public  void  setCreated(Date created){this.created = created;}

@Basic
@Column(name = "role_id")
public Integer getRoleId(){return roleId;} public void setRoleId(Integer roleId){this.roleId = roleId;}

private Role role;
@ManyToOne
@JoinColumn(name = "role_id", insertable = false , updatable = false)
public Role getRole(){
    return  role;
}
public void setRole(Role role){this.role = role;}
}
