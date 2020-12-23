package com.sap.service;

import java.util.ArrayList

;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.sap.config.MailConfig;
import com.sap.exception.RecordNotFoundException;
import com.sap.model.Product;
import com.sap.model.SoldProduct;
import com.sap.repository.ProductRepository;
import com.sap.repository.SoldProductRepository;
import com.sap.web.UserController;


public class ProductService {

	ProductRepository repository;
	
	SoldProductRepository spRepository;
	
	String to="stanislava1505@gmail.com";
	String from="writersplaceowner@gmail.com";
	String password="korqosnnnzffibhv";
	
	
	public long ID;
	
	public ProductService() {

	}
	
	public ProductService(ProductRepository repository, SoldProductRepository spRepository) {
		this.repository=repository;
		this.spRepository = spRepository;
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
/*	public Product createPromotion1(Product entity) {
		Optional<Product> product = repository.findById(entity.getId());
		if(product.isPresent()) 
		{
			Product newEntity = product.get();
			newEntity.setPromotion(entity.getPromotion());
			newEntity = repository.save(newEntity);

			return newEntity;
		}
		return entity;
		
	}
	*/
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
				newEntity.setPrice(entity.getPrice());
				int k = entity.getQuantity();
				String pr= entity.getType() + " " + entity.getModel();
				System.out.println(k);
				soldProduct(k, pr);
				if(k==0) {
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
	public void soldProduct(int quantity, String model) {
		if(quantity == 0) {
			String soldModel = String.valueOf(model);
			spRepository.save(new SoldProduct(soldModel));
		}
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
			System.out.println(finalPrice2);
			newEntity.setPrice(finalPrice2);
			newEntity = repository.save(newEntity);
		}
		
		
	}

	public void saleProduct(Product entity) throws AddressException, MessagingException {
		entity.setId(ID);
		ID=0;
		Optional<Product> product = repository.findById(entity.getId());
		if(product.isPresent()) 
		{
			Product newEntity = product.get();
			int kl = newEntity.getQuantity();
			System.out.println(kl);
			int fl = kl-1;
			MailService sendEmail = new MailService();
			
			if(fl>1) {
			newEntity.setQuantity(fl);
			sendEmail.getMailProperties();
			if(fl<1) {
				newEntity.setQuantity(0);
				sendEmail.getMailProperties();
			}
			newEntity.setDate(entity.getDate());
			System.out.println(newEntity.getDate());
			System.out.println(newEntity.getQuantity());
			}
			newEntity = repository.save(newEntity);
		}
		
	}
	
	public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return repository.findAll(keyword);
        }
        return repository.findAll();
    }
	
	
}