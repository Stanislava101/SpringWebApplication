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
import com.sap.model.Client;
import com.sap.service.ClientService;

@Controller
public class ClientController {
	@Autowired
	ClientService service;
	
	
	@RequestMapping(path = {"/editClient", "/editClient/{id}"})
	public String editClientById(Model model, @PathVariable("id") Optional<Long> id) 
							throws RecordNotFoundException 
	{
		
		System.out.println("editClientById = " + id);
		if (id.isPresent()) {
			Client entity = service.getClientById(id.get());
			model.addAttribute("client", entity);
			
		} else {
			model.addAttribute("client", new Client());
		}
		
		
		return "add-edit-client";
	}
	
	@RequestMapping(path = "/clientsData")
	public String getAllClients(Model model) 
	{	
		System.out.println("getAllClients");
		
		List<Client> list = service.getAllClients();

		model.addAttribute("clients", list);
		
		return "list-clients";
	}
	@RequestMapping(path = "/deleteClient/{id}")
	public String deleteProductById(Model model, @PathVariable("id") Long id) 
							throws RecordNotFoundException 
	{
		
		System.out.println("deleteClientById = " + id);
		
		service.deleteClientById(id);
		return "redirect:/";
	}

	@RequestMapping(path = "/createClient", method = RequestMethod.POST)
	public String createOrUpdateProduct(Client client) 
	{
		service.createOrUpdateClient(client);
		return "redirect:/";
	}
}