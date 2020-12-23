package com.sap.config;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.sap.service.ProductService;
import com.sun.mail.smtp.SMTPTransport;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

@Configuration 
public class MailConfig {
	//String to="stanislava1505@gmail.com";//change accordingly
//	String from="writersplaceowner@gmail.com";//change accordingly
//	String password="korqosnnnzffibhv";//change accordingly

  //  @Bean
  //  public JavaMailSender javaMailService() throws AddressException, javax.mail.MessagingException {
    	
   //     JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

   //     javaMailSender.setHost("smtp.gmail.com");
  //      javaMailSender.setPort(25);
  //      javaMailSender.setJavaMailProperties(getMailProperties());

   //     return javaMailSender;
  //  }
    

  //  public Properties getMailProperties() throws AddressException, javax.mail.MessagingException {
    //	Properties props = new Properties();
   // 	props.put("mail.smtp.host", "smtp.gmail.com");
   // 	props.put("mail.smtp.socketFactory.port", "465");
   // 	props.put("mail.smtp.socketFactory.class",
    //	"javax.net.ssl.SSLSocketFactory");
    //	props.put("mail.smtp.auth", "true");
    //	props.put("mail.smtp.port", "465");

    //	Session session = Session.getDefaultInstance(props,
    //	new javax.mail.Authenticator() {
   // 	protected PasswordAuthentication getPasswordAuthentication() {
   // 	return new PasswordAuthentication(from,password);
    //	}
    //	});

    //	MimeMessage message = new MimeMessage(session);
   // 	message.setFrom(new InternetAddress(from));
    //	message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
    //	message.setSubject("Nothing Special..");
    //	message.setText("Send Mail By Java Programmm....");

    	//send message
    	//Transport.send(message);

    //	System.out.println("message sent successfully");

    //	return props;
  //  	}
	
}
