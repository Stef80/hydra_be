package net.agm.hydra.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.agm.hydra.model.Projects;
import net.agm.hydra.repository.ProjectsRepository;
import net.agm.hydra.services.ProjectsService;

public class ProjectsServiceImpl implements ProjectsService {
	
	@Autowired
	ProjectsRepository projectRepositoy;

	@Override
	public Projects getProjectById(Long id) {	
		return projectRepositoy.findById(id).orElse(null);
	}

	@Override
	public List<Projects> getAll() {
		
		return projectRepositoy.findAll();
	}

	@Override
	public Projects newProject(Projects p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Projects updateProject(Projects p) {
		// TODO Auto-generated method stub
		return null;
	}

}
