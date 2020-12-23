package com.sap.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class JavaMailSender {
	 
    @Autowired
    private JavaMailSender mailSender;
     
    public void sendEmail() {
        // use mailSender here...
    } 
}
