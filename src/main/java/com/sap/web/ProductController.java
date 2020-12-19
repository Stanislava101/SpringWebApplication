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
import com.sap.model.Product;
import com.sap.service.ProductService;

@Controller
public class ProductController {
	   @Autowired
	    ProductService service;
	
	@RequestMapping(path = {"/edit", "/edit/{id}"})
	public String editProductById(Model model, @PathVariable("id") Optional<Long> id) 
							throws RecordNotFoundException 
	{
		
		System.out.println("editProductById = " + id);
		if (id.isPresent()) {
			Product entity = service.getProductById(id.get());
			model.addAttribute("product", entity);
			
		} else {
			model.addAttribute("product", new Product());
		}
		
		
		return "add-edit-product";
	}
	
	@RequestMapping(path = "/productsData")
	public String getAllEmployees(Model model) 
	{	
		System.out.println("getAllProducts");
		
		List<Product> list = service.getAllProducts();

		model.addAttribute("products", list);
		
		return "list-products";
	}
	@RequestMapping(path = "/delete/{id}")
	public String deleteProductById(Model model, @PathVariable("id") Long id) 
							throws RecordNotFoundException 
	{
		
		System.out.println("deleteProductById = " + id);
		
		service.deleteProductById(id);
		return "redirect:/";
	}

	@RequestMapping(path = "/createProduct", method = RequestMethod.POST)
	public String createOrUpdateProduct(Product product) 
	{
		service.createOrUpdateProduct(product);
		return "redirect:/";
	}
	
}
