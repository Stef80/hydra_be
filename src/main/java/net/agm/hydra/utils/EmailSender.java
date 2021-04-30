package net.agm.hydra.utils;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;

import net.agm.hydra.services.EmailService;

@Component
public class EmailSender implements EmailService{




	Logger logger = LoggerFactory.getLogger(this.getClass());

	private final Properties properties = System.getProperties();
	@Autowired
	private Environment env;


	public void sendMessage(String to, String subject, String body) throws MessagingException {
		setMailProperties(properties, env);

		logger.info("Authentication of " + properties.getProperty("mail.smtp.username") + "...");
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(properties.getProperty("mail.smtp.username"),
						properties.getProperty("mail.smtp.password"));
			}
		});

		logger.info("Authentication complete");

		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress(properties.getProperty("mail.smtp.username")));
		if(!(to.equals("")))
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		mimeMessage.setSubject(subject);
		mimeMessage.setText(body);
		mimeMessage.setContent(body, "text/html; charset=utf-8");

		Transport transport = session.getTransport(properties.getProperty("mail.smtp.protocol"));
		logger.info("properties host " + properties.getProperty("mail.smtp.host"));
		logger.info("properties username " + properties.getProperty("mail.smtp.username"));
		transport.connect(properties.getProperty("mail.smtp.host"), properties.getProperty("mail.smtp.username"),
				properties.getProperty("mail.smtp.password"));

		logger.info("Sending email " + "to " + to);

		try {
			transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
		} catch (MailException me) {
			logger.info("MailException: " + me.getMessage());
		}
		logger.info("Email sent");

	}

	public void setMailProperties(Properties properties, Environment env) {
		properties.put("mail.smtp.host", env.getProperty("spring.mail.host"));
		properties.put("mail.smtp.port", env.getProperty("spring.mail.port"));
		properties.put("mail.smtp.ssl.enable", env.getProperty("spring.mail.properties.mail.smtp.ssl.enable"));
		properties.put("mail.smtp.starttls.enable", env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
		properties.put("mail.smtp.protocol", env.getProperty("spring.mail.protocol"));
		properties.put("mail.smtp.username", env.getProperty("spring.mail.username"));
		properties.put("mail.smtp.password", env.getProperty("spring.mail.password"));
	}


}
