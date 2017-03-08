package com.arms.app.user;

/**
 * Created by arms20170106 on 7/2/2560.
 */
import com.arms.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.NoSuchAlgorithmException;

@Controller
public class SignupController {
    @Autowired
    UserService userService;
    @ModelAttribute
    UserAddForm setUserAddForm(){return new UserAddForm();}
    @RequestMapping("/user/signUp")
    public ModelAndView signup(ModelAndView modelAndView) {
        modelAndView.setViewName("user/sign_up");
        modelAndView.addObject("userAddForm", new UserAddForm());
        return modelAndView;
    }
    @RequestMapping("/user/add")
    public Object add(@Validated UserAddForm userAddForm, BindingResult bindingResult, RedirectAttributes attributes) throws NoSuchAlgorithmException {
        if(bindingResult.hasErrors()) {
                return "user/sign_up";
            }
            userService.createUser(userAddForm);
            attributes.addFlashAttribute("messageDialog", "User was created.");
       return "redirect:/user/login";
    }
}