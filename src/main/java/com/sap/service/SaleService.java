package com.sap.service;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.sap.exception.ClientNotFoundException;
import com.sap.model.Client;
import com.sap.model.Product;
import com.sap.model.Sales;
import com.sap.model.SoldProduct;
import com.sap.repository.ProductRepository;
import com.sap.repository.SalesRepository;
import com.sap.repository.SoldProductRepository;
public class SaleService {
	
	ProductRepository repository;
	SoldProductRepository spRepository;
	SalesRepository salesRepository;
	public SaleService(ProductRepository repository, SoldProductRepository spRepository, SalesRepository salesRepository) {
		this.repository=repository;
		this.spRepository = spRepository;
		this.salesRepository = salesRepository;
	}
	public void soldProduct(int quantity, String model, double price, String date) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		if(quantity == 0) {
			String soldModel = String.valueOf(model);
			spRepository.save(new SoldProduct(soldModel, price, date,username));
		}
	}
	
	@Transactional
	public void sale(String representativeName, String product, Double price, String date) throws ClientNotFoundException {
		Client cc = new Client(ClientService.name,ClientService.email,ClientService.phoneNumber,representativeName);
		int quantity=1;
		Sales oneSale = new Sales(cc);
		Set<Sales> itemsSet = new HashSet<Sales>();
			itemsSet.add(oneSale);	
			salesRepository.save(new Sales(representativeName,product, price, quantity,date,cc));
	}
	public void checkIfClientExist() throws ClientNotFoundException {
		if(ClientService.name==null) {	
				throw new ClientNotFoundException("Enter a client");
				}
	}

}
