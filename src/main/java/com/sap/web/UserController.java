package com.sap.web;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.SystemProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sap.exception.RecordNotFoundException;
import com.sap.model.Product;
import com.sap.model.User;
import com.sap.service.ProductService;
import com.sap.service.UserService;
import com.sap.service.SRepresentativeService;
import com.sap.validator.UserValidator;
@Controller
public class UserController {
    private UserService userService;
    private UserValidator userValidator;


    public UserController(UserService userService,UserValidator userValidator) {
    	this.userService=userService;
    	this.userValidator=userValidator;
    }
	
	
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }
    @RequestMapping(value="/admin", method = RequestMethod.GET)
    	public ModelAndView adminHome() {
    		ModelAndView modelAndView = new ModelAndView();
    		modelAndView.setViewName("admin");
    		return modelAndView;
    	}
    

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        return "redirect:/h2-console";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    
    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String homePage(Model model) {
        return "index";
    }
    
}