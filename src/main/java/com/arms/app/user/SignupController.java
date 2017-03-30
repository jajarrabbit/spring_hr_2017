package com.arms.app.user;

/**
 * Created by arms20170106 on 7/2/2560.
 */
import com.arms.domain.entity.Role;
import com.arms.domain.repository.Rolerepository;
import com.arms.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class SignupController {
    @Autowired
    UserService userService;
    @Autowired
    Rolerepository rolerepository;
    @ModelAttribute
    UserAddForm setUserAddForm(){return new UserAddForm();}
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/user/signUp")
    public ModelAndView signup(ModelAndView modelAndView) {
        List<Role> roleList = rolerepository.findAll();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("/user/sign_up");
        modelAndView.addObject("userAddForm", new UserAddForm());
        return modelAndView;
    }
    @RequestMapping("/user/add")
    public Object add(ModelAndView modelAndView,@Validated UserAddForm userAddForm, BindingResult bindingResult, RedirectAttributes attributes) throws NoSuchAlgorithmException {
        if(userService.checkEmail(userAddForm) == 1)
        {
            bindingResult.rejectValue("E-mail","messageError","This Email unuseable" );
        }
        if(bindingResult.hasErrors()) {
            List<Role> roleList = rolerepository.findAll();
            modelAndView.addObject("roleList",roleList);
            modelAndView.setViewName("/user/sign_up");
            return modelAndView;
            }else {
            userService.createUser(userAddForm);
            attributes.addFlashAttribute("messageDialog", "User was created.");
            modelAndView.setViewName("redirect:/user/login");
            return modelAndView;
        }
    }
}