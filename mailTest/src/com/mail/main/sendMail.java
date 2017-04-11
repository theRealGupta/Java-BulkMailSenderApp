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
			+ "\n \n I am looking for the job change as a iOS developer. I got you your email from Linkedin. "
			+ "\n I have experienced in iOS App development using Objective C And Swift. "
			+ "\n \n Thank And Regards"
			+ "\n Anup Gupta "
			+ "\n 8604675834";
//	String message = "Hi,";
	
	String subject = " iOS Developer (Objective C / Swift) 1.5 year experience + 6 month Training in iOS";
	
	
	String filePath = "E:\\AnupGupta(iOS with ObjectiveC _ Swift).pdf";
	String fileName = "AnupGupta(iOS with ObjectiveC _ Swift).pdf";
	
	
	
	
	
	
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
