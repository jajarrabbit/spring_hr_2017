package com.arms.domain.service;

/**
 * Created by arms20170106 on 28/3/2560.
 */
public class AuthorityRoleNotFoundException extends RuntimeException {

    public  AuthorityRoleNotFoundException(){
        super();
    }

    public AuthorityRoleNotFoundException(String message){super(message);}
}
