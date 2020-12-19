package com.sap.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sap.model.Client;

@Repository
public interface ClientRepository 
			extends CrudRepository<Client, Long> {

}
