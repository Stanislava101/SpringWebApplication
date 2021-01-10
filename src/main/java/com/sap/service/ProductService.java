package com.sap.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.sap.exception.ClientNotFoundException;
import com.sap.exception.RecordNotFoundException;
import com.sap.model.Client;
import com.sap.model.Product;
import com.sap.model.Sales;
import com.sap.model.SoldProduct;
import com.sap.repository.ClientRepository;
import com.sap.repository.ProductRepository;
import com.sap.repository.SalesRepository;
import com.sap.repository.SoldProductRepository;
import com.sap.repository.UserRepository;

public class ProductService {

	ProductRepository repository;
	
	SoldProductRepository spRepository;
	
	SalesRepository salesRepository;
	
	UserRepository userRepository;
	
	ClientRepository clRepository;
	
	String to="stanislava1505@gmail.com";
	String from="writersplaceowner@gmail.com";
	String password="korqosnnnzffibhv";
	
	
	private long ID;
	
	public ProductService() {

	}
	
	public ProductService(ProductRepository repository, SoldProductRepository spRepository, SalesRepository salesRepository, UserRepository userRepository, ClientRepository clRepository) {
		this.repository=repository;
		this.spRepository = spRepository;
		this.salesRepository = salesRepository;
		this.userRepository = userRepository;
		this.clRepository=clRepository;
	}
	
	public List<Product> getAllProducts()
	{
		List<Product> result = (List<Product>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Product>();
		}
	}

	public Product getProductById(Long id) throws RecordNotFoundException 
	{
		Optional<Product> product = repository.findById(id);
		if(product.isPresent()) {
			ID = id;
			return product.get();
		} else {
			throw new RecordNotFoundException("No record exist for given id");
		}
	}
	public Product createOrUpdateProduct(Product entity) 
	{
		System.out.println("createOrUpdateProduct");
		// Create new entry 
		entity.setId(ID);
		ID=0;
		if(entity.getId()  == null) 
		{
			System.out.println(entity.getId());
			entity = repository.save(entity);
		return entity;
		} 
		else
		{
			// update existing entry 
			Optional<Product> product = repository.findById(entity.getId());
			
			if(product.isPresent()) 
			{
				Product newEntity = product.get();
				newEntity.setType(entity.getType());
				newEntity.setModel(entity.getModel());
				double price = entity.getPrice();
				DecimalFormat df = new DecimalFormat("#.##");      
				 price= Double.valueOf(df.format(price));
				newEntity.setPrice(price);
				
				int newQuantity = entity.getQuantity();
				String pr= entity.getType() + " " + entity.getModel();
				if(newQuantity==0) {
					System.out.println("SOLD");
				}
				newEntity.setQuantity(entity.getQuantity());
					
				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);
			}
		}
	
		return entity;
	} 
	
	public void deleteProductById(Long id) throws RecordNotFoundException 
	{
		System.out.println("deleteEmployeeById");
		
		Optional<Product> product = repository.findById(id);
		
		if(product.isPresent()) 
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No record exist for given id");
		}
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
	public void sale(String representativeName, String product, Double price, int quantity, String date) throws ClientNotFoundException {
		Client cc = new Client(ClientService.name,ClientService.email,ClientService.phoneNumber,representativeName);
		Sales oneSale = new Sales(cc);
		Set<Sales> itemsSet = new HashSet<Sales>();
		if(ClientService.name==null) {	
			throw new ClientNotFoundException("Enter a client");
		}
		itemsSet.add(oneSale);	
		salesRepository.save(new Sales(representativeName,product, price, quantity,date,cc));
				
	}
	public void createPromotion(Product entity) {
		entity.setId(ID);
		ID=0;
		Optional<Product> product = repository.findById(entity.getId());
		if(product.isPresent()) 
		{
			Product newEntity = product.get();
			newEntity.setPromotion(entity.getPromotion());
			newEntity = repository.save(newEntity);
			double finalPrice = newEntity.getPromotion()*newEntity.getPrice();
			double finalPrice2 = newEntity.getPrice()-finalPrice;
			DecimalFormat df = new DecimalFormat("#.##");      
			 finalPrice2= Double.valueOf(df.format(finalPrice2));
			newEntity.setPrice(finalPrice2);
			newEntity = repository.save(newEntity);
		}
		
		
	}

	public void saleProduct(Product entity) throws AddressException, MessagingException, ClientNotFoundException {
		entity.setId(ID);
		ID=0;
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();

		Optional<Product> product = repository.findById(entity.getId());
		System.out.println("User id is = " + username);
		if(product.isPresent()) 
		{
			Product newEntity = product.get();
			int quantity = newEntity.getQuantity();
			int quantityResult = quantity-1;
			MailService sendEmail = new MailService();
			String productData = newEntity.getType() + " " + newEntity.getModel();
			if(quantityResult>=0) {
			newEntity.setQuantity(quantityResult);
			newEntity.setDate(entity.getDate());
			soldProduct(quantityResult,productData, newEntity.getPrice(), newEntity.getDate());
			sale(username,productData,newEntity.getPrice(), newEntity.getQuantity(),newEntity.getDate());
			sendEmail.getMailProperties();
			if(quantityResult<0) {
				newEntity.setQuantity(0);
				sendEmail.getMailProperties();
			}
			System.out.println(newEntity.getDate());
			System.out.println(newEntity.getQuantity());
			}
			newEntity = repository.save(newEntity);
		}
		
	}
}