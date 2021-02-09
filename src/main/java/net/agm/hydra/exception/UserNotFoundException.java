package net.agm.hydra.exception;

public class UserNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		this("this user does not exist");
	}
	
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
