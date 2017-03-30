package com.arms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by arms20170106 on 29/3/2560.
 */
@Controller
public class ErrorController {

    @RequestMapping(value ="/403")
    public  String forbidden(){return "403";}

    @RequestMapping(value ="/400")
    public  String badRequest(){return "400";}

    @RequestMapping(value = "/404")
    public  String notFound(){return "404";}

    @RequestMapping(value = "/413")
    public  String entityToLarge(){return "413";}

    @RequestMapping(value = "/500")
    public ModelAndView internalServerError(ModelAndView modelAndView,Exception exception)
    {
        modelAndView.addObject("showException", true);
        modelAndView.addObject("exception",exception);
        modelAndView.setViewName("500");
        return modelAndView;
    }
}
