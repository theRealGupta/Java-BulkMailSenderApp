package com.mail.main;

import javax.mail.MessagingException;

import com.mail.test.MailSender;
import com.mail.test.SendAttachmentInEmail;

public class sendMail {
public static void main(String[] args) {
	
	
	String recipients[] = {
			"abcd@gmail.com",
			"xyz@gmail.com"  
			};
	

	String message = "Hi,"
			+ "\n \n type your mail here "
			+ "\n this is 2nd line "
			+ "\n \n Thank And Regards"
			+ "\n Anup Gupta "
			+ "\n 8604675834";
//	String message = "Hi,";
	
	String subject = " Subject Line ";
	
	
	String filePath = "E:\\File path if any";
	String fileName = "file name ";
	
	
	
	
	
	
	/*
	 * MailSender mailSender = new MailSender();
	 * try {
		mailSender.sendSSLMessage(recipients, message);
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	
	
	SendAttachmentInEmail sendAttachmentInEmail = new SendAttachmentInEmail();
	try {
		sendAttachmentInEmail.sendSSLMessage(recipients, message,subject,filePath,fileName);
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
