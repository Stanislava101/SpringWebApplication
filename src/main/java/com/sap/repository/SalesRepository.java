package com.sap.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.model.Sales;

@Repository
public interface SalesRepository 
			extends JpaRepository<Sales, Long> {
}
