package net.agm.hydra.services;

import javax.mail.MessagingException;

public interface EmailService {

	
	void sendMessage(String to, String subject, String body)throws MessagingException ;
}
