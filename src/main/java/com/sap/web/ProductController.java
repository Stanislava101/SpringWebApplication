package com.sap.web;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
	@RequestMapping(path = {"/promotion/{id}"})
	public String promotionProductById(Model model, @PathVariable("id") Optional<Long> id) 
							throws RecordNotFoundException 
	{
		
		if (id.isPresent()) {
			Product entity = service.getProductById(id.get());
			model.addAttribute("product", entity);
			
		} else {
			model.addAttribute("product", new Product());
		}
		
		
		return "add-promotion";
	}
	
	@RequestMapping(path = "/productsData")
	public String getAllEmployees(Model model) 
	{	
		System.out.println("getAllProducts");
		String keyword = "1";
		List<Product> list = service.getAllProducts();

		model.addAttribute("products", list);
		
		return "list-products";
	}
	
	
	@RequestMapping(path = "/saleProducts")
	public String getAllProductsForSale(Model model) 
	{	
		System.out.println("getAllProducts");
		
		List<Product> list = service.getAllProducts();

		model.addAttribute("products", list);
		
		return "list-salesProducts";
	}
	
	@RequestMapping(path = "/soldProductsData")
	public String getAllSoldProducts(Model model) 
	{	
		System.out.println("getAllProducts");
		
		List<Product> list = service.getAllProducts();

		model.addAttribute("products", list);
		
		return "soldProducts";
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
	
	@RequestMapping(path = "/createPromotion", method = RequestMethod.POST)
	public String createPromotion(Product entity) 
	{
		service.createPromotion(entity);
		return "redirect:/";
	}
	
	@RequestMapping(path = {"/sales/{id}"})
	public String saleProduct(Model model, @PathVariable("id") Optional<Long> id) 
							throws RecordNotFoundException 
	{
		
		if (id.isPresent()) {
			Product entity = service.getProductById(id.get());
			model.addAttribute("product", entity);
			
		} else {
			model.addAttribute("product", new Product());
		}
		
		
		return "saleProduct";
	}
	@RequestMapping(path = "/saleProduct", method = RequestMethod.POST)
	public String saleProduct(Product product) throws AddressException, MessagingException 
	{
		service.saleProduct(product);
		return "redirect:/";
	}
	
	@RequestMapping(path = "/filterData")
	//public String getFilterData(Model model, @Param("keyword") String keyword) 
	public String getFilterData(Model model)
	{	
		String keyword="TV";
		List<Product> list = service.listAll(keyword);

		model.addAttribute("products", list);
		
		return "filterData";
	}
	
	@RequestMapping(path = {"/view/{id}"})
	public String viewProduct(Model model, @PathVariable("id") Optional<Long> id) 
							throws RecordNotFoundException 
	{
		
		if (id.isPresent()) {
			Product entity = service.getProductById(id.get());
			model.addAttribute("product", entity);
			
		} else {
			model.addAttribute("product", new Product());
		}
		
		
		return "view-product";
	}
	
}
