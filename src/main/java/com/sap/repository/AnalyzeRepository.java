package com.sap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.sap.model.Analyze;


public interface AnalyzeRepository extends CrudRepository<Analyze, Long> {

}
