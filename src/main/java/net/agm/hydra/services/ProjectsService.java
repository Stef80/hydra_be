package net.agm.hydra.services;

import java.util.List;

import net.agm.hydra.exception.ProjectException;
import net.agm.hydra.model.Projects;

public interface ProjectsService {
	
	
	Projects getProjectById(Long id) throws ProjectException;
	
	List<Projects> getAll();
	
	Projects newProject(Projects p) throws ProjectException;
	
	Projects updateProject(Projects p) throws ProjectException;
	
	
	Projects deleteProjectById(Long id)throws ProjectException;
	
	
	

}
