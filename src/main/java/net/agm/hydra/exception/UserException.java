package net.agm.hydra.exception;

public class UserException extends RuntimeException {

	public UserException() {
		this("User Alredy exist");
		// TODO Auto-generated constructor stub
	}

	public UserException(String message) {
		super(message);
		
	}
	


	
}
