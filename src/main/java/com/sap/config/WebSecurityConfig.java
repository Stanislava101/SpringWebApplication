package com.sap.config;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sap.model.Role;
import com.sap.model.User;
import com.sap.repository.ProductRepository;
import com.sap.repository.RoleRepository;
import com.sap.repository.SoldProductRepository;
import com.sap.repository.UserRepository;
import com.sap.service.ProductService;
import com.sap.service.UserDetailsServiceImpl;
import com.sap.service.UserService;
import com.sap.service.UserServiceImpl;
import com.sap.validator.UserValidator;
import com.sap.service.ProductService;
import com.sap.web.UserController;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint; 
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserServiceImpl getService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        return new UserServiceImpl(userRepository, roleRepository, bCryptPasswordEncoder);
    }
    @Bean
    public UserDetailsServiceImpl getDetailsService(UserRepository userRepository) {
    	return new UserDetailsServiceImpl(userRepository);
    }
    @Bean
    public UserValidator setValidator(UserService userService) {
    	return new UserValidator(userService);
    }
    
    @Bean
    public ProductService productService(ProductRepository repository) {
    	return new ProductService(repository);
    }
    


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/resources/**", "/registration").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/log_in/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }
  

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
 
    }
}
