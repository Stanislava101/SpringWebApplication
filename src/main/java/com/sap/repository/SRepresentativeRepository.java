package com.sap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sap.model.Product;
import com.sap.model.User;
@Repository
public interface SRepresentativeRepository 
			extends JpaRepository<User, Long> {

}