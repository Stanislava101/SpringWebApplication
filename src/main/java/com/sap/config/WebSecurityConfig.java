package com.sap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.sap.service.SaleService;
import com.sap.service.UserDetailsServiceImpl;
import com.sap.service.UserService;
import com.sap.service.UserServiceImpl;
import com.sap.validator.ClientValidator;
import com.sap.validator.ProductValidator;
import com.sap.validator.UserValidator;
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
    public ProductService productService(ProductRepository repository, SoldProductRepository spRepository, SalesRepository salesRepository, UserRepository userRepository, ClientRepository clRepository) {
    	return new ProductService(repository, spRepository,salesRepository, userRepository, clRepository);
    }
    
    @Bean
    public SaleService saleService(ProductRepository repository, SoldProductRepository spRepository, SalesRepository salesRepository) {
    	return new SaleService(repository,spRepository,salesRepository);
    }
    
    @Bean
    public SRepresentativeService representativeService(SRepresentativeRepository repository, RoleRepository roleRepository,BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
    	return new SRepresentativeService(repository, roleRepository, bCryptPasswordEncoder, userRepository);
    }
    
    @Bean
    public ClientService clientService(ClientRepository repository, SalesRepository slRepository) {
    	return new ClientService(repository, slRepository);
    }
    
    @Bean
    public ClientValidator setCValidator(ClientService userService) {
    	return new ClientValidator(userService);
    }
    
    @Bean
    public ProductValidator setPValidator(ProductService pService) {
    	return new ProductValidator(pService);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/resources/**", "/login").permitAll()
                    .antMatchers("/resources/**", "/index").permitAll()
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
                    .antMatchers("/adminSalesData/**").hasRole("ADMIN")
                    .antMatchers("/adminSearchByPeriod/**").hasRole("ADMIN")
                    .antMatchers("/productsDataList/**").hasRole("ADMIN")
                    .antMatchers("/registerSRepresentatives/**").hasRole("USER")
                    .antMatchers("/representativeSearchByDay/**").hasRole("USER")
                    .antMatchers("/search/**").hasRole("ADMIN")
                    .antMatchers("/search2/**").hasRole("USER")
                    .antMatchers("/searchBySalesRepresentative/**").hasRole("ADMIN")
                    .antMatchers("/searchResults").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                    .exceptionHandling().accessDeniedPage("/404")
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
