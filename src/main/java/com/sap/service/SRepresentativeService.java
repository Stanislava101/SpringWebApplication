package com.sap.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.sap.exception.RecordNotFoundException;
import com.sap.model.Role;
import com.sap.model.User;
import com.sap.repository.RoleRepository;
import com.sap.repository.SRepresentativeRepository;
import com.sap.repository.UserRepository;

public class SRepresentativeService implements SRepresentativeService2 {
	SRepresentativeRepository repository;
	RoleRepository roleRepository;
	UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private long ID;
	
	public SRepresentativeService(SRepresentativeRepository repository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
		this.repository=repository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder =bCryptPasswordEncoder;
		this.userRepository = userRepository;
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
			Role role = roleRepository.findByName("ROLE_USER");
			Set<Role> roles = new HashSet<>();
			roles.add(role);
			entity.setRoles(roles);
			userRepository.save(entity);
			entity = userRepository.save(entity);
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
				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity.setPassword(entity.getPassword());
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

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role role = roleRepository.findByName("ROLE_USER");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		userRepository.save(user);
			}

	@Override
	public User findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
}
