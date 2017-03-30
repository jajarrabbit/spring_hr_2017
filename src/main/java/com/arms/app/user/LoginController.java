package com.arms.app.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by arms20170106 on 7/2/2560.
 */
@Controller
public class LoginController {
    @RequestMapping("/user/login")
    public ModelAndView login(ModelAndView modelAndView) throws IOException{
        modelAndView.setViewName("user/login");
        return modelAndView;
    }
    @RequestMapping(value = "/user/successLogin", method = RequestMethod.GET)
    public Object successLogin(ModelAndView model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.setViewName("/user/login");
            return model;
        }
        redirectAttributes.addFlashAttribute("message", "Login Successful");
        model.addObject("loginSuccess", true);
        return "redirect:/";
    }
    @RequestMapping(value = "/user/failLogin", method = RequestMethod.GET)
    public ModelAndView failLogin(ModelAndView model) {
        model.addObject("errorMsg", "Login failed, Invalid name and/or passwod");
        model.addObject("loginFail", true);
        model.setViewName("user/login");
        return model;
    }
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }
}