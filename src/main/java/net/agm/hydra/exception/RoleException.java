package net.agm.hydra.exception;

public class RoleException extends RuntimeException {

	public RoleException() {
		super("Role alredy assigned");
		
	}

	public RoleException(String message) {
		super(message);
		
	}
	
	
	

}
