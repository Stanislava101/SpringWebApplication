package com.sap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.model.SoldProduct;

public interface SoldProductRepository extends JpaRepository<SoldProduct, Long> {

}
