package com.sap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sap.exception.RecordNotFoundException;
import com.sap.model.Role;
import com.sap.model.User;
import com.sap.repository.ProductRepository;
import com.sap.repository.RoleRepository;

import com.sap.repository.SRepresentativeRepository;

public class SRepresentativeService {
	SRepresentativeRepository repository;
	RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	public long ID;
	
	public SRepresentativeService(SRepresentativeRepository repository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		//roleRepository.save(new Role("ROLE_USER"));
		this.repository=repository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder =bCryptPasswordEncoder;
	}

	public List<User> getAllProducts()
	{
		List<User> result = (List<User>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<User>();
		}
	}

	public User getProductById(Long id) throws RecordNotFoundException 
	{
		Optional<User> product = repository.findById(id);
		if(product.isPresent()) {
			ID = id;
			return product.get();
		} else {
			throw new RecordNotFoundException("No record exist for given id");
		}
	}
	
	public User createOrUpdateProduct(User entity) 
	{
		System.out.println("createOrUpdateProduct");
		// Create new entry 
		entity.setId(ID);
		ID=0;
		if(entity.getId()  == null) 
		{
			System.out.println(entity.getId());
			entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			entity = repository.save(entity);
		return entity;
		} 
		else
		{
			// update existing entry 
			Optional<User> product = repository.findById(entity.getId());
			
			if(product.isPresent()) 
			{
				User newEntity = product.get();
				newEntity.setUsername(entity.getUsername());
				newEntity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
				//newEntity.setPassword(entity.getPassword());
				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
				entity = repository.save(entity);
			}
		}
	
		return entity;
	} 
	
	public void deleteProductById(Long id) throws RecordNotFoundException 
	{
		System.out.println("deleteEmployeeById");
		
		Optional<User> product = repository.findById(id);
		
		if(product.isPresent()) 
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No record exist for given id");
		}
	} 
}
