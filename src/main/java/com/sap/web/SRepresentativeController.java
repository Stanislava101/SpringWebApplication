package com.sap.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sap.exception.RecordNotFoundException;
import com.sap.model.User;
import com.sap.service.SRepresentativeService;

@Controller
public class SRepresentativeController {
	
	   @Autowired
	   SRepresentativeService SRepresentativeService;
	@RequestMapping(path = {"/editSRepresentative", "/editSRepresentative/{id}"})
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
	
	@RequestMapping(path = "/srepresentativesData")
	public String getAllSRepresentatives(Model model) 
	{	
		System.out.println("getAllSRepresentatives");
		
		List<User> list = SRepresentativeService.getAllProducts();

		model.addAttribute("srepresentative", list);
		
		return "list-SRepresentative";
	}
	
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
