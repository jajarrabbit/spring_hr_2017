package com.arms.app.home;

/**
 * Created by arms20170106 on 12/1/2560.
 */

import org.springframework.stereotype.Controller;

        import org.springframework.web.bind.annotation.RequestMapping;

        import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping("/one")

    public ModelAndView home4(ModelAndView modelAndView) {

        modelAndView.setViewName("one");

        return modelAndView;

    }
    @RequestMapping("/two")

    public ModelAndView home5(ModelAndView modelAndView) {

        modelAndView.setViewName("two");

        return modelAndView;

    }
    @RequestMapping("/three")

    public ModelAndView home6(ModelAndView modelAndView) {

        modelAndView.setViewName("three");

        return modelAndView;

    }
}