package net.agm.hydra.exception;

public class UpdateException extends RuntimeException {

	public UpdateException() {
		this("Update not found");
	}

	public UpdateException(String message) {
		super(message);
		
	}



}
