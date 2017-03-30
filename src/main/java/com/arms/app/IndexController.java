package com.arms.app;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by arms20170106 on 29/3/2560.
 */
@Controller
public class IndexController implements ErrorController {

    private static final String PATH = "/error";

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = PATH)
    public ModelAndView error(ModelAndView modelAndView, HttpServletRequest request) {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode==500){
            Exception e = (Exception)request.getAttribute("javax.servlet.error.exception");
            Throwable cause = e.getCause();
            List<Throwable> causeList = new LinkedList<>();
            logger.error("              #####  500 STATUS #####              ");
            while(null != cause) {
                causeList.add(cause);
                cause = cause.getCause();
                logger.error(String.valueOf(cause));
            }

            for (StackTraceElement stackTrace : e.getStackTrace()){
                logger.error(String.valueOf(stackTrace));
            }

            modelAndView.addObject("showException", true);
            modelAndView.addObject("exception", e);
            modelAndView.addObject("causeList", causeList);
        }
        modelAndView.setViewName(statusCode.toString());
        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value = "/showDialog")
    public String showDialog(RedirectAttributes redirectAttributes) {
        return "messageDialog";

    }
}
