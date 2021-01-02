package com.sap.validator;
import javax.mail.internet.InternetAddress;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sap.model.Client;
import com.sap.service.ClientService;

import javax.mail.internet.AddressException;
public class ClientValidator implements Validator {
	
	   private ClientService userService;
	    public ClientValidator(ClientService userService) {
	        this.userService = userService;
	    }

	    @Override
	    public boolean supports(Class<?> aClass) {
	        return Client.class.equals(aClass);
	    }

	    @Override
	    public void validate(Object o, Errors errors) {
	        Client user = (Client) o;
	        String phoneNumber = user.getPhoneNumber();
	        if (!user.getEmail().contains("@")) {
	            errors.rejectValue("email", "Diff.userForm2.email");
	        }
	        
	        if (!phoneNumber.matches("^(\\d{3}[- .]?){2}\\d{4}$")) {
	            errors.rejectValue("phoneNumber", "Diff.userForm2.phoneNumber");
	        }
	    }

}
