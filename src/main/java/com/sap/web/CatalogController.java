package com.sap.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sap.model.Product;
import com.sap.service.ProductService;
import com.sap.service.SaleService;

public class CatalogController {
	/*@Autowired
    ProductService service;
   
   @Autowired
    SaleService saleService;
   
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
	*/
}
