package net.agm.hydra.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import net.agm.hydra.services.EmailService;

@Component
public class EmailSender implements EmailService{
	
	@Autowired
	JavaMailSender mailSender;
	
	@Override
	public void sendMessage(String to,String subject, String body ) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("hydra.noreply@agmsolutions.net");
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(body);
		
		mailSender.send(msg);
	}

}
