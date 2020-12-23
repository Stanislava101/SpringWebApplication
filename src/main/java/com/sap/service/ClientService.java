package com.sap.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.sap.exception.RecordNotFoundException;
import com.sap.model.Client;
import com.sap.model.Role;
import com.sap.repository.ClientRepository;
import com.sap.repository.RoleRepository;


public class ClientService {
	ClientRepository repository;
	
	public long ID;
	
	public ClientService() {

	}
	
	public ClientService(ClientRepository repository) {
		this.repository=repository;
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
			Optional<Client> client = repository.findById(entity.getId());
			
			if(client.isPresent()) 
			{
				Client newEntity = client.get();
				newEntity.setName(entity.getName());
				newEntity.setEmail(entity.getEmail());
				newEntity.setPhoneNumber(entity.getPhoneNumber());
				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);
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
}
