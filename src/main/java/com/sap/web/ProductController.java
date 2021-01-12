package com.sap.web;

import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sap.exception.ClientNotFoundException;
import com.sap.exception.RecordNotFoundException;
import com.sap.model.Product;
import com.sap.service.ProductService;
import com.sap.service.SaleService;
import com.sap.validator.ProductValidator;


@Controller
public class ProductController {
	   @Autowired
	    ProductService service;
	   
	   @Autowired
	   SaleService saleService;
	   
	   @Autowired
	    private ProductValidator pValidator;

	@RequestMapping(path = {"/edit/{id}"})
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
	
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String pForm(Model model) {
        model.addAttribute("productForm", new Product());
        return "add-edit-product";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String pFormAddData(@ModelAttribute("productForm") Product productForm, BindingResult bindingResult, Model model) {
        pValidator.validate(productForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "add-edit-product";
        }
        service.createOrUpdateProduct(productForm);
        return "redirect:/";
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
	
    @RequestMapping(value = "/promotion/{id}", method = RequestMethod.GET)
    public String promotion(Model model,  @PathVariable("id") Optional<Long> id) throws RecordNotFoundException{
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
	
	@RequestMapping(path = "/productsDataList")
	public String getAllProductsList(Model model) 
	{	
		System.out.println("getAllProducts");
		List<Product> list = service.getAllProducts();
		model.addAttribute("products", list);
		return "list-products_admin";
	}
	
	@RequestMapping(path = "/chart")
	public String getCharts(Model model) 
	{	
		List<Product> list = service.getAllProducts();
		model.addAttribute("products", list);
		return "chart";
	}
	
	@RequestMapping(path = "/chartLine")
	public String getChartLine(Model model) 
	{	
		List<Product> list = service.getAllProducts();
		model.addAttribute("products", list);
		return "chartLine";
	}
	
	@RequestMapping(path = "/chartPie")
	public String getChartPie(Model model) 
	{	
		List<Product> list = service.getAllProducts();
		model.addAttribute("products", list);
		return "chartPie";
	}
	
	@RequestMapping(path = "/soldProductsData")
	public String getAllSoldProducts(Model model) 
	{	
		System.out.println("getAllProducts");
		List<Product> list = service.getAllProducts();
		model.addAttribute("products", list);
		return "soldProducts";
	}
	
	@RequestMapping(path = "/adminSalesData")
	public String getAllSalesData(Model model) 
	{	
		System.out.println("getAllProducts");
		List<Product> list = service.getAllProducts();
		model.addAttribute("products", list);
		return "admin-sales";
	}
	
	@RequestMapping(path = "/adminSearchByPeriod")
	public String adminSearchByPeriod(Model model) 
	{	
		List<Product> list = service.getAllProducts();
		model.addAttribute("products", list);
		return "admin-searchbyperiod";
	}
	
	@RequestMapping(path = "/representativeSearchByDay")
	public String representativeSearchByDay(Model model) 
	{	
		List<Product> list = service.getAllProducts();
		model.addAttribute("products", list);
		return "representative-searchbyday";
	}
	
	@RequestMapping(path = "/search")
	public String searchProducts(Model model) 
	{	
		List<Product> list = service.getAllProducts();
		model.addAttribute("products", list);
		return "search";
	}
	
	@RequestMapping(path = "/search2")
	public String search2Products(Model model) 
	{	
		List<Product> list = service.getAllProducts();
		model.addAttribute("products", list);
		return "search2";
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
	public String saleProduct(Product product) throws AddressException, MessagingException, ClientNotFoundException 
	{
		service.saleProduct(product);
		return "redirect:/";
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