package com.edvantis.workopolis.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

public class SendMessage {
	
	static Logger log = Logger.getLogger(SendMessage.class.getName());
	
	public static void send(String emailTo, String subject, String message){
		log.info("start send");
		log.debug("emailTo "+emailTo);
		log.debug("subject "+subject);
		log.debug("message "+message);
		ModelAndView modelAndView = new ModelAndView();	
		
		final String username = "workopolis3@gmail.com";
		final String password = "123456qwerty!";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		javax.mail.Session mailSession = javax.mail.Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {
			Message mMessage = new MimeMessage(mailSession);
			mMessage.setFrom(new InternetAddress("workopolis3@gmail.com"));
			mMessage.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailTo));
			mMessage.setSubject(subject);
			
			mMessage.setText(message);
			Transport.send(mMessage);
			log.debug("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		modelAndView.setViewName("login");
		log.debug("password has been sent to email");
		
	}
	
	public static String getFullURL(HttpServletRequest context) {
		String host = "";
		String url="";
		String port;
		host = context.getServerName()+":";
		log.debug("host==> " + host);
		port = context.getServerPort()+"";
		log.debug("port==> " + port);
		url = context.getContextPath()+"/";
		log.debug("url==> " + url);
		String fullURL = "http://" + host + port + url;
		log.debug("fullURL==> " + fullURL);
		return fullURL;
	}
	

}
