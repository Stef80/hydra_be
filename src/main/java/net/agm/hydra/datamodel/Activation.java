package net.agm.hydra.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Activation {
	@JsonProperty("active")
	ACTIVE("active"),
	@JsonProperty("toConfirm")
	TO_CONFIRM("toConfirm"),
	@JsonProperty("inactive")
	INACTIVE("inactive");
	
	public final String label;

	private Activation(String label) {
		this.label = label;
	}

}

