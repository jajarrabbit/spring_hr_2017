package com.arms.domain.service;

import com.arms.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by arms20170106 on 7/2/2560.
 */
@Service
public class AppService {
    @Autowired
    UserRepository userRepository;
}
