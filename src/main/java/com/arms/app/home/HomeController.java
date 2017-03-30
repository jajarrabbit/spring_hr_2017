package com.arms.app.home;

/**
 * Created by arms20170106 on 12/1/2560.
 */
import com.arms.domain.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

@Autowired
    MailService mailService;


    @RequestMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("/index");
        return modelAndView;
    }

    @RequestMapping("/index")
    public ModelAndView home1(ModelAndView modelAndView) {
        modelAndView.setViewName("/index");
        return modelAndView;
    }

    @RequestMapping("/leaveList")
    public ModelAndView home6(ModelAndView modelAndView) {
        modelAndView.setViewName("leaveList");
        return modelAndView;
    }
}