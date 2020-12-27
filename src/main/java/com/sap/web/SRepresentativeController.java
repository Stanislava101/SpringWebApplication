package com.sap.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sap.exception.RecordNotFoundException;
import com.sap.model.User;
import com.sap.service.SRepresentativeService;
import com.sap.service.SRepresentativeService2;
import com.sap.service.UserService;
import com.sap.validator.UserValidator;

@Controller
public class SRepresentativeController {
	
	@Autowired
	SRepresentativeService2 service2;
	@Autowired
    private UserValidator userValidator;

	   @Autowired
	   SRepresentativeService SRepresentativeService;
	   
	@RequestMapping(path = {"/editSRepresentative","/editSRepresentative/{id}"})
	public String editSRepresentativeById(Model model, @PathVariable("id") Optional<Long> id) 
							throws RecordNotFoundException 
	{
		
		System.out.println("editSRepresentativeById = " + id);
		if (id.isPresent()) {
			User entity = SRepresentativeService.getProductById(id.get());
			model.addAttribute("srepresentative", entity);
			
		} else {
			model.addAttribute("srepresentative", new User());
		}
		
		
		return "add-edit-srepresentative";
	}
	
	
	
	
	
	   
	    @RequestMapping(value = "/srepresentativesData", method = RequestMethod.GET)
	    public String registration(Model model) {
	        model.addAttribute("userForm", new User());

	        return "list-SRepresentative";
	    }

	    @RequestMapping(value = "/srepresentativesData", method = RequestMethod.POST)
	    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
	        userValidator.validate(userForm, bindingResult);

	        if (bindingResult.hasErrors()) {
	            return "list-SRepresentative";
	        }

	        service2.save(userForm);

	        return "redirect:/h2-console";
	    }

	   
	
/*	
	@RequestMapping(path = "/srepresentativesData")
	public String getAllSRepresentatives(Model model) 
	{	
		System.out.println("getAllSRepresentatives");
		
		List<User> list = SRepresentativeService.getAllProducts();

		model.addAttribute("srepresentative", list);
		
		return "list-SRepresentative";
	}
	*/
	
	@RequestMapping(path = "/deleteSR/{id}")
	public String deleteSRepresentativeById(Model model, @PathVariable("id") Long id) 
							throws RecordNotFoundException 
	{
		
		System.out.println("deleteSRepresentativeById = " + id);
		
		SRepresentativeService.deleteProductById(id);
		return "redirect:/";
	}

	
	@RequestMapping(path = "/createSRepresentative", method = RequestMethod.POST)
	public String createOrUpdateSRepresentative(User user) 
	{
		SRepresentativeService.createOrUpdateProduct(user);
		return "redirect:/";
	}
}
