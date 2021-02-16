package net.agm.hydra.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {

	@JsonProperty("open")
	OPEN("open"),
	@JsonProperty("in progres")
	IN_PROGRESS("in progress"),
	@JsonProperty("closed")
	CLOSED("closed");


	public final String label;

	private Status(String label) {
		this.label = label;
	}
}
