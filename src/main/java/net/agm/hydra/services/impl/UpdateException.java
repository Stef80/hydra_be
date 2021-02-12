package net.agm.hydra.services.impl;

public class UpdateException extends RuntimeException {

	public UpdateException() {
		this("Update not found");
	}

	public UpdateException(String message) {
		super(message);
		
	}



}
