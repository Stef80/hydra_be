package net.agm.hydra.datamodel;

public enum Status {
	
	
	OPEN("open"),
	IN_PROGRESS("in progress"),
	CLOSED("closed");
    
	
	 public final String label;

    private Status(String label) {
        this.label = label;
    }
}
