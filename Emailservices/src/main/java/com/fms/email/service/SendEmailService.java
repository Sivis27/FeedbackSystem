package com.fms.email.service;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;

import com.fms.email.constants.FMSUtils;
import com.fms.email.entity.EmailInfo;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class SendEmailService {

	 Properties mailServerProperties;
	 Session getMailSession;
	 MimeMessage generateMailMessage;


	public  void generateAndSendEmail(EmailInfo emailInfo, String filePathStr) throws AddressException, MessagingException {

		// Step1
		log.info("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");

		// Step2
		log.info("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		
	
	   

		if(emailInfo.getMailType().equalsIgnoreCase(FMSUtils.BULK_MAIL)) {
			String[] arr = emailInfo.getToAddressList().toArray(new String[0]); 
			   
			// String[] recipientList = recipients.split(",");
			InternetAddress[] recipientAddress = new InternetAddress[arr.length];
			int counter = 0;
			for (String recipient : arr) {
				recipientAddress[counter] = new InternetAddress(recipient.trim());
				counter++;
			}
			
			generateMailMessage.setRecipients(Message.RecipientType.TO, recipientAddress);

		}else {
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("sivismercury@gmail.com"));
			generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("sivasankart24@gmail.com"));

		}
		
	
		// generateMailMessage.addRecipients(Message.RecipientType.TO, new
		// InternetAddress(recipients));
		generateMailMessage.setSubject(emailInfo.getEventsubject());

		// 3) create MimeBodyPart object and set your message text
		BodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setText(emailInfo.getEventbody());

		// 4) create new MimeBodyPart object and set DataHandler object to this object
		MimeBodyPart messageBodyPart2 = new MimeBodyPart();

		log.info(filePathStr);
		
		String filename = "/home/ubuntu/JAVA_Projects/Excels/UserInfo/UserInfo.xlsx";// change accordingly
		DataSource source = new FileDataSource(filePathStr);
		messageBodyPart2.setDataHandler(new DataHandler(source));
		messageBodyPart2.setFileName(emailInfo.getFilename());

		// 5) create Multipart object and add MimeBodyPart objects to this object
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart1);
		multipart.addBodyPart(messageBodyPart2);

		// 6) set the multiplart object to the message object
		generateMailMessage.setContent(multipart);
		// Step3
		log.info("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");

		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", "thangavelsamykalimuthugounder@gmail.com", "Thangam12345$");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
		log.info("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
	}
}