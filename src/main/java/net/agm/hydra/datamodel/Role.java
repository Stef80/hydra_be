package net.agm.hydra.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {

	@JsonProperty("admin")
	ADMIN("admin"),
	@JsonProperty("worker")
	WORKER("worker"),
	@JsonProperty("guest")
	GUEST("guest");

	public final String label;

	private Role(String label) {
		this.label = label;
	}
}
