package com.sap.service;

import java.util.HashSet;
import java.util.Set;
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

    	this.userRepository=userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;

   
    }

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role role = roleRepository.findByName("ROLE_ADMIN");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		userRepository.save(user);
			}

	@Override
	public User findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
	
	public Long idSearch(User user) {
		return user.getId();
	}




}
