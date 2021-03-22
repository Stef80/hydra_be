package net.agm.hydra.exception;

public class LicenseException extends RuntimeException {

	public LicenseException() {
	    this("No license found");
	}

	public LicenseException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
   
	
}
