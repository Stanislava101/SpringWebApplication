package com.sap.service;

import java.util.ArrayList

;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.sap.exception.RecordNotFoundException;
import com.sap.model.Product;
import com.sap.model.SoldProduct;
import com.sap.repository.ProductRepository;
import com.sap.repository.SoldProductRepository;
import com.sap.web.UserController;


public class ProductService {

	ProductRepository repository;
	
	public long ID;
	
	public ProductService(ProductRepository repository) {
		this.repository=repository;
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
				newEntity.setQuantity(entity.getQuantity());
				newEntity.setPrice(entity.getPrice());
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
}