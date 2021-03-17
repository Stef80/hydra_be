package net.agm.hydra.services;

import java.util.List;

import net.agm.hydra.model.Bookables;

public interface BookablesService {
	
	List<Bookables> getAll();
	
	Bookables newBookable(String name, String description, String tenantId);
	
	

}
