package com.sap.service;

import java.util.ArrayList

;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import com.sap.model.User;
import com.sap.repository.ProductRepository;
import com.sap.repository.SoldProductRepository;
import com.sap.repository.UserRepository;
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
				soldProduct(k, pr, entity.getPrice(), entity.getDate());
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
	public void soldProduct(int quantity, String model, double price, String date) {
		if(quantity == 0) {
			String soldModel = String.valueOf(model);
			spRepository.save(new SoldProduct(soldModel, price, date,(long)1));
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
/*
	
	public LinkedHashMap<Integer, String> sortHashMapByValues(
	        HashMap<Integer, String> passedMap) {
	    List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
	    List<String> mapValues = new ArrayList<>(passedMap.values());
	    Collections.sort(mapValues);
	    Collections.sort(mapKeys);

	    LinkedHashMap<Integer, String> sortedMap =
	        new LinkedHashMap<>();

	    Iterator<String> valueIt = mapValues.iterator();
	    while (valueIt.hasNext()) {
	        String val = valueIt.next();
	        Iterator<Integer> keyIt = mapKeys.iterator();

	        while (keyIt.hasNext()) {
	            Integer key = keyIt.next();
	            String comp1 = passedMap.get(key);
	            String comp2 = val;

	            if (comp1.equals(comp2)) {
	                keyIt.remove();
	                sortedMap.put(key, val);
	                break;
	            }
	        }
	    }
	    return sortedMap;
	}
	public void topSoldProducts(Product entity) {
		int br1=0;
		int br2=0;
		int br3=0;
		int br4=0;
		int br5=0;
		if(entity.getType().equals("TV")) {
			int m = br1;
			br1++;
			System.out.println("TV == " + br1);
			System.out.println("TV2 == " + m);
		}
		else if(entity.getType().equals("PC")) {
			br2++;
			System.out.println("PP == " + br2);
		}else if(entity.getType().equals("Mobile phone")) {
			br3++;
			System.out.println("Mobile phone == " + br3);
		}else if(entity.getType().equals("Laptop")) {
			br4++;
			System.out.println("Laptop == " + br4);
		}else if(entity.getType().equals("Headphones")) {
			br5++;
			System.out.println("Headphones" + br5);
		}
		
		//Collection<Integer> list = new LinkedList<Integer>(); 
		  HashMap<Integer,String> list = new HashMap<Integer,String>();
		  list.put(br1,"TV1");
		  list.put(br2,"PC");
		  list.put(br3,"Mobile phone");
		  list.put(br4,"Laptop");
		  list.put(br5,"Headphones");
		  sortHashMapByValues(list);
		  for (String i : list.values()) {
		      System.out.println("key: " + i + " value: " + list.get(i));
		    }

	}
	*/

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
			if(fl>=0) {
			newEntity.setQuantity(fl);
			newEntity.setDate(entity.getDate());
			//newEntity.setDate(entity.getDate());
			soldProduct(fl,newEntity.getModel(), newEntity.getPrice(), newEntity.getDate());
		//	topSoldProducts(newEntity);
			sendEmail.getMailProperties();
			if(fl<0) {
				newEntity.setQuantity(0);
				sendEmail.getMailProperties();
			}
			//newEntity.setDate(entity.getDate());
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