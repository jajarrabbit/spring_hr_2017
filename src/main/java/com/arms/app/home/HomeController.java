package com.arms.app.home;

/**
 * Created by arms20170106 on 12/1/2560.
 */

import com.arms.app.leavehistory.LeaveHistoryForm;
import com.arms.domain.entity.LeaveHistory;
import com.arms.domain.repository.LeaveHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {



    @RequestMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/index")
    public ModelAndView home1(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/elements")
    public ModelAndView home3(ModelAndView modelAndView) {
        modelAndView.setViewName("elements");
        return modelAndView;
    }


    @RequestMapping("/three")
    public ModelAndView home6(ModelAndView modelAndView) {
        modelAndView.setViewName("three");
        return modelAndView;
    }
}