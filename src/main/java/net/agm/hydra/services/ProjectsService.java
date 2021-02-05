package net.agm.hydra.services;

import java.util.List;

import net.agm.hydra.model.Projects;

public interface ProjectsService {
	
	
	Projects getProjectById(Long id);
	
	List<Projects> getAll();
	
	Projects newProject(Projects p);
	
	Projects updateProject(Projects p);
	
	
	
	

}
