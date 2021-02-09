package net.agm.hydra.datamodel;

public enum Role {

	
  ADMIN("admin"),
  WORKER("worker"),
  GUEST("guest");
	
	 public final String label;

	    private Role(String label) {
	        this.label = label;
	    }
}
