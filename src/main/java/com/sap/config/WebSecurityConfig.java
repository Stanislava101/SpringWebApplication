package com.sap.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sap.model.Role;
import com.sap.model.User;
import com.sap.repository.ClientRepository;
import com.sap.repository.ProductRepository;
import com.sap.repository.RoleRepository;
import com.sap.repository.SRepresentativeRepository;
import com.sap.repository.SalesRepository;
import com.sap.repository.SoldProductRepository;
import com.sap.repository.UserRepository;
import com.sap.service.ClientService;
import com.sap.service.ProductService;
import com.sap.service.SRepresentativeService;
import com.sap.service.UserDetailsServiceImpl;
import com.sap.service.UserService;
import com.sap.service.UserServiceImpl;
import com.sap.validator.ClientValidator;
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
    @Primary
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
    public ProductService productService(ProductRepository repository, SoldProductRepository spRepository, SalesRepository salesRepository, UserRepository userRepository) {
    	return new ProductService(repository, spRepository,salesRepository, userRepository);
    }
    
    @Bean
    public SRepresentativeService representativeService(SRepresentativeRepository repository, RoleRepository roleRepository,BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
    	return new SRepresentativeService(repository, roleRepository, bCryptPasswordEncoder, userRepository);
    }
    
    @Bean
    public ClientService clientService(ClientRepository repository) {
    	return new ClientService(repository);
    }
    
    @Bean
    public ClientValidator setCValidator(ClientService userService) {
    	return new ClientValidator(userService);
    }
    
   // @Bean
   // public SendEmailService emailService(JavaMailSender javaMailSender) {
   // 	return new SendEmailService(javaMailSender);
   // }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/resources/**", "/login").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/h2-console/**").permitAll()

                    .antMatchers("/registration/**").hasRole("ADMIN")
                    .antMatchers("/srepresentativesData/**").hasRole("ADMIN")
                    .antMatchers("/editSRepresentative/**").hasRole("ADMIN")
                    .antMatchers("/edit/**").hasRole("ADMIN")
                    .antMatchers("/clientsData/**").hasRole("USER")
                    .antMatchers("/editClient/**").hasRole("USER")
                    .antMatchers("/saleProducts/**").hasRole("USER")
                    .antMatchers("/sales/**").hasRole("USER")
                    .antMatchers("/soldProductsData/**").hasRole("USER")
                    .antMatchers("/productsData/**").hasRole("USER")
                    .antMatchers("/productsDataList/**").hasRole("ADMIN")
                    .antMatchers("/promotion/**").hasRole("USER")
                    .antMatchers("/view/**").hasRole("USER")
                    
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
