package com.sap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sap.model.Product;
@Repository
public interface ProductRepository 
			extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p WHERE p.type LIKE %?1%")
	public List<Product> findAll(String keyword);

}
