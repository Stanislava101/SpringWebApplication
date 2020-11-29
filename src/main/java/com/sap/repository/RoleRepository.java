package com.sap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
