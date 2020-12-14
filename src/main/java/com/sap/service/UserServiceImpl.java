package com.sap.service;

import java.util.HashSet;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sap.model.Role;
import com.sap.model.User;
import com.sap.repository.RoleRepository;
import com.sap.repository.UserRepository;


public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
  //  	roleRepository.save(new Role("ROLE_USER"));
    	this.userRepository=userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(user);
			}

	@Override
	public User findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
	
	/*
	public String smth(User user) {
		String m = "";
		String reply = user.getName();
		if(reply.equals("ROLE_USER")) {
			m="ROLE_USER";
		}else if(reply.equals("ROLE_ADMIN")) {
			 m = "ROLE_ADMIN";
	}
		return reply;
	}
	*/

}
