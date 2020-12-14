package com.sap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sap.model.Role;
import com.sap.model.User;
import com.sap.repository.RoleRepository;

@SpringBootApplication
public class SapEcommerceProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(SapEcommerceProjectApplication.class, args);
	}
	}
//	@Bean
//	public CommandLineRunner func2(RoleRepository repository) {
//	return args->{
	//	repository.save(new Role("ROLE_ADMIN"));
//		};
//	}}
	
/*
	@Bean
public CommandLineRunner func(RoleRepository repository) {
	int a=2;
	String m;
	if(a==2) {
		m="ROLE_USER";
		a++;
	}else {
		 m = "ROLE_ADMIN";
}
	return args->{
	repository.save(new Role(m));
	};
}
*/

