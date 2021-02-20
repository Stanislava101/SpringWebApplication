package com.sap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.sap.exception.RecordNotFoundException;
import com.sap.model.Client;
import com.sap.model.Sales;
import com.sap.repository.ClientRepository;
import com.sap.repository.SalesRepository;


public class ClientService {
	ClientRepository repository;
	SalesRepository slRepository;
	protected static String email;
	protected static String phoneNumber;
	protected static String name;
	private long ID;
	
	public ClientService(ClientRepository repository, SalesRepository slRepository) {
		this.repository=repository;
		this.slRepository=slRepository;
	}
	
	public List<Client> getAllClients()
	{
		List<Client> result = (List<Client>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Client>();
		}
	}

	public Client getClientById(Long id) throws RecordNotFoundException 
	{
		Optional<Client> client = repository.findById(id);
		if(client.isPresent()) {
			ID = id;
			return client.get();
		} else {
			throw new RecordNotFoundException("No record exist for given id");
		}
	}
	
	public Client createOrUpdateClient(Client entity) 
	{
		System.out.println("createOrUpdateClient");
		// Create new entry 
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		entity.setId(ID);
		entity.setRepresentative(username);
		 email = entity.getEmail();
		 phoneNumber = entity.getPhoneNumber();
		 name = entity.getName();
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
			Optional<Client> client = repository.findById(entity.getId());
			
			if(client.isPresent()) //check if there is a value inside the Optional object
			{
				Client newEntity = client.get();
				newEntity.setName(entity.getName());
				newEntity.setEmail(entity.getEmail());
				newEntity.setPhoneNumber(entity.getPhoneNumber());
				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
		//		entity = repository.save(entity);
			}
		}
	
		return entity;
	} 
	
	public void deleteClientById(Long id) throws RecordNotFoundException 
	{
		System.out.println("deleteClientById");
		
		Optional<Client> product = repository.findById(id);
		
		if(product.isPresent()) 
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No record exist for given id");
		}
	} 
	
	public void deleteSaleById(Long id) throws RecordNotFoundException 
	{
		System.out.println("deleteSaleById");
		
		Optional<Sales> product = slRepository.findById(id);
		
		if(product.isPresent()) 
		{
			slRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No record exist for given id");
		}
	} 
}