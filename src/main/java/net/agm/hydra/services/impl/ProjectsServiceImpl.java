package net.agm.hydra.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.agm.hydra.model.Projects;
import net.agm.hydra.repository.ProjectsRepository;
import net.agm.hydra.services.ProjectsService;
@Service
public class ProjectsServiceImpl implements ProjectsService {

	@Autowired
	ProjectsRepository projectRepositoy;

	@Override
	public Projects getProjectById(Long id) {	
		if(id != null)
			return projectRepositoy.findById(id).orElse(null);
		return null;
	}

	@Override
	public List<Projects> getAll() {
		return projectRepositoy.findAll();
	}

	@Override
	public Projects newProject(Projects p) {
		Projects c = null;
		if(p!= null && p.getProjectId() == 0) {
			c =  projectRepositoy.save(p);
		}	
		return c;
	}

	@Override
	public Projects updateProject(Projects p) {
		if(p != null && getProjectById(p.getProjectId()) != null) {
			return	projectRepositoy.save(p);
		}

		return null;
	}

	@Override
	public Projects deleteProjectById(Long id) {
		Projects p = getProjectById(id);
		if(p != null) {
			projectRepositoy.deleteById(id);
			return p;
		}
		return null;
	}

}
