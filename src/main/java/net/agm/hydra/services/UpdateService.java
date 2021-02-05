package net.agm.hydra.services;

import java.util.List;

import net.agm.hydra.model.Updates;

public interface UpdateService  {
	
	
	List<Updates> getAll();
	
	Updates addUpdates(Updates u);

}
