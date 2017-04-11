package com.mail.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendAttachmentInEmail {
	public void sendSSLMessage(String recipients[],String msgBody,String msgSub,String filePath,String Fname) throws MessagingException 
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

for(String to:recipients){
	try{
    // Create a default MimeMessage object.
    Message message = new MimeMessage(session);

    // Set From: header field of the header.
    message.setFrom(new InternetAddress(emailFromAddress));

    // Set To: header field of the header.
    message.setRecipients(Message.RecipientType.TO,
       InternetAddress.parse(to));

    // Set Subject: header field
    message.setSubject(msgSub);

    // Create the message part
    BodyPart messageBodyPart = new MimeBodyPart();

    // Now set the actual message
    messageBodyPart.setText(msgBody);

    // Create a multipar message
    Multipart multipart = new MimeMultipart();

    // Set text message part
    multipart.addBodyPart(messageBodyPart);

    // Part two is attachment
    messageBodyPart = new MimeBodyPart();
    String filename = filePath;
    DataSource source = new FileDataSource(filename);
    messageBodyPart.setDataHandler(new DataHandler(source));
    messageBodyPart.setFileName(Fname);
    multipart.addBodyPart(messageBodyPart);

    // Send the complete message parts
    message.setContent(multipart);

    // Send message
    Transport.send(message);

    System.out.println("Sent message successfully...."+to);
	}
	catch(Exception e){
		System.out.println("Sent message failed...."+to);
	}
	}
}
}
