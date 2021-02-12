package net.agm.hydra.exception;

public class ProjectException extends RuntimeException {

	public ProjectException() {
		this("Project not found");
		
	}

	public ProjectException(String message) {
		super(message);
		
	}
	
	

}
