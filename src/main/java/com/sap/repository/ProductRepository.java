package com.sap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sap.model.Product;
@Repository
public interface ProductRepository 
			extends JpaRepository<Product, Long> {

}
