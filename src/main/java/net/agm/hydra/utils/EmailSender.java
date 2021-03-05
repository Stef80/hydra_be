package net.agm.hydra.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailSender {
	
	@Autowired
	JavaMailSender mailSender;
	
	
	public void send(String to,String body ) {
		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setTo(to);
		msg.setText(body);
		
		mailSender.send(msg);
	}

}
