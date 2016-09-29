package com.edvantis.workopolis.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.apache.log4j.Logger;

public class EmailSendingService {
	
	static Logger log = Logger.getLogger(SendMessage.class.getName());
	
	public String createInterviewEmailSubject() {
		return "New Interview Scheduled";
	}
	
	public String createInterviewEmailMessage(String date, String firstName, String lastName, String vacancyTitle, String company, String correcpondingEmail){
		StringBuilder interviewMessage = new StringBuilder("Dear " + firstName + " " + lastName);
		interviewMessage.append("/n");
		interviewMessage.append("An interview has been scheduled for you. Please sign in to your Workopolis account for more details.");
		interviewMessage.append("/n");
		interviewMessage.append("For Vacancy " + vacancyTitle + ", " + company);
		interviewMessage.append("/n");
		interviewMessage.append("Interview Date: " + date);
		interviewMessage.append("/n");
		interviewMessage.append("Contact Email: " + correcpondingEmail);
	
		return interviewMessage.toString();
	}
	
	public String createOfferEmailSubject(){
		return "New Job Offer Right for You!";
	}
	
	public String createOfferEmailMessage(String date, String firstName, String lastName, String vacancyTitle,  String company, String correcpondingEmail){
		StringBuilder offerMessage = new StringBuilder("Dear " + firstName + " " + lastName);
		offerMessage.append("/n");
		offerMessage.append("A job offer has been proposed to you. Please sign in to your Workopolis account and give your responce.");
		offerMessage.append("/n");
		offerMessage.append("For Vacancy " + vacancyTitle + ", " + company);
		offerMessage.append("/n");
		offerMessage.append("Offer Date: " + date);
		offerMessage.append("/n");
		offerMessage.append("Contact Email: " + correcpondingEmail);
		
		return offerMessage.toString();
	}
	
	public void sendEmail(String mailTo, String message, String subject){
		log.info("start send");
		
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
				InternetAddress.parse(mailTo));
			mMessage.setSubject(subject);
			mMessage.setText(message);
			Transport.send(mMessage);
			log.debug("Email sent");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
