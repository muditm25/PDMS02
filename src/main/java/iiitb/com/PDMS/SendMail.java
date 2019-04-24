package iiitb.com.PDMS;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import bean.Package;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;


public class SendMail {
	
	public static String sendOne(String emailid,Package pkg) {
		
		String recipient = emailid;
		
		String sender = "teegalasarika27@gmail.com";
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		properties.put("mail.smtp.socketFactory.port", "587");
		
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, "hdossnqeqcaxjapk");
			}
		});
		
		session.setDebug(true);
		
		try {
			
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(sender));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			
			message.setSubject("Test Mail");
			message.setText("Package at the reception!!!");
			
			
			Transport.send(message);
			
			return "Success!";
		}
		catch(MessagingException mex) {
			mex.printStackTrace();
			return "Failure!";
		}
	}
	
	
	public static String sendMultiple(String emailid,Package pkg) {
		
		String recipient = emailid;
		
		String sender = "teegalasarika27@gmail.com";
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		properties.put("mail.smtp.socketFactory.port", "587");
		
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, "hdossnqeqcaxjapk");
			}
		});
		
		session.setDebug(true);
		
		try {
			
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(sender));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			
			message.setSubject("Test Mail");
			message.setText("Package at the reception!!!");
			
			
			Transport.send(message);
			
			return "Success!";
		}
		catch(MessagingException mex) {
			mex.printStackTrace();
			return "Failure!";
		}
	}
}
