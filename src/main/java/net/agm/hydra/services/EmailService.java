package net.agm.hydra.services;

public interface EmailService {

	
	void sendMessage(String to, String subject, String body);
}
