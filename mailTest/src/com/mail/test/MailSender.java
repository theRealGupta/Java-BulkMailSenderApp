package com.mail.test;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class MailSender{
	
	
	

	
	public void sendSSLMessage(String recipients[],String message) throws MessagingException 
	{
		String subject, from;
		subject="Test Subject";
		boolean debug = true;
		Properties prop = new Properties();
		InputStream input = null;
		Set<Entry<Object, Object>> value=null;

		try {
			String filename = "properties_en.properties";
			input = getClass().getClassLoader().getResourceAsStream( "com/mail/resources/properties_en.properties");
		// load a properties file
			prop.load(input);
 
		// get the property value and print it out
			value=prop.entrySet();
 
	} catch (IOException ex) {
		ex.printStackTrace();
	} 
	
	String SMTP_HOST_NAME = prop.getProperty("SMTP_HOST_NAME");
	String SMTP_PORT= prop.getProperty("SMTP_PORT");
	final String emailFromAddress= prop.getProperty("emailFromAddress");
	final String emailPassword= prop.getProperty("emailPassword");
	
	Properties props = new Properties();
	props.put("mail.smtp.host", SMTP_HOST_NAME);
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.debug", "true");
	props.put("mail.smtp.port", SMTP_PORT);
	props.put("mail.smtp.socketFactory.port", SMTP_PORT);
	/*props.put("mail.smtp.socketFactory.class", SSL_FACTORY);*/
	/*props.put("mail.smtp.socketFactory.fallback", "false");*/

	Session session = Session.getDefaultInstance(props,
	new javax.mail.Authenticator() {

	protected PasswordAuthentication getPasswordAuthentication() {
	return new PasswordAuthentication(emailFromAddress,emailPassword);
	}
	});

	session.setDebug(debug);

	Message msg = new MimeMessage(session);
	InternetAddress addressFrom = new InternetAddress(emailFromAddress);
	msg.setFrom(addressFrom);

	InternetAddress[] addressTo = new InternetAddress[recipients.length];
	for (int i = 0; i < recipients.length; i++) {
	addressTo[i] = new InternetAddress(recipients[i]);
	}
	msg.setRecipients(Message.RecipientType.TO, addressTo);

	// Setting the Subject and Content Type
	msg.setSubject(subject);

	//setting message mime type to text/html
	msg.setContent(message, "text/html");
	Transport.send(msg);
}
	
	

}
