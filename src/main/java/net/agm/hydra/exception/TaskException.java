package net.agm.hydra.exception;

public class TaskException extends RuntimeException {

	public TaskException() {
	   this("Task not found");
	}

	public TaskException(String arg0) {
		super(arg0);
		
	}

	

	

}
