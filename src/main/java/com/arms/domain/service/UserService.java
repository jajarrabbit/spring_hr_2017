package com.arms.domain.service;

import com.arms.app.component.PasswordEncoder;
import com.arms.app.user.UserAddForm;
import com.arms.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by arms20170106 on 7/2/2560.
 */
@Service
public class UserService extends AppService {
    @Autowired
    PasswordEncoder passwordEncoder;
    public void createUser(UserAddForm userAddForm) throws NoSuchAlgorithmException {
        Date nowDate = Calendar.getInstance().getTime();
        User user = new User();
        user.setUsername(userAddForm.getUsername());
        user.setEmail(userAddForm.getEmail());
        user.setPassword(passwordEncoder.hashMD5(userAddForm.getPassword()));
        user.setCreated(nowDate);
        user.setRoleId(userAddForm.getRoleId());
        userRepository.save(user);
    }

    public int checkEmail(UserAddForm userAddForm)
    {
        List<User> userList = userRepository.findAll();
        for (User userL : userList)
        {
            if(userAddForm.getEmail().equals(userL.getEmail()))
            {
                return 1;
            }
        }
        return 0;
    }
}