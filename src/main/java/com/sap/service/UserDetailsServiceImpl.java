package com.sap.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.sap.model.User;
import com.sap.repository.UserRepository;
import com.sap.model.Role;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	private UserRepository userRepository;
	
	
    public UserDetailsServiceImpl() {
    }
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository =userRepository;
    }
	
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
   /* private List < GrantedAuthority > buildUserAuthority(Set < Role > userRoles) {

        Set < GrantedAuthority > setAuths = new HashSet < GrantedAuthority > ();

        // add user's authorities
        for (Role userRole: userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getName()));
        }

        List < GrantedAuthority > Result = new ArrayList < GrantedAuthority > (setAuths);

        return Result;
    }
    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
    */
}