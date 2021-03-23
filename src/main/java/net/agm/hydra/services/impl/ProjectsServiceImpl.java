package net.agm.hydra.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.agm.hydra.exception.ProjectException;
import net.agm.hydra.model.Projects;
import net.agm.hydra.model.dto.ProjectDto;
import net.agm.hydra.repository.ProjectsRepository;
import net.agm.hydra.services.ProjectsService;
@Service
public class ProjectsServiceImpl implements ProjectsService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	ProjectsRepository projectRepositoy;

	@Override
	public Projects getProjectById(Long id) {	
		if(id != null && id > 0) {
			logger.info("projectService-getProjectById");
			return projectRepositoy.findById(id).orElseThrow(ProjectException :: new);
		}
		 throw new ProjectException();
	}

	@Override
	public List<Projects> getAll() {
		logger.info("projectService-getAll");
		return projectRepositoy.findAll();
	}

	@Override
	public Projects newProject(Projects p) {
		Projects c = null;
		logger.info("projectService-newProject");
		if(p!= null && p.getId() == null) {
			c =  projectRepositoy.save(p);
		}	
		return c;
	}

	@Override
	public Projects updateProject(Projects p) {
		logger.info("projectService-updateProject"+ p);
		if(p != null && getProjectById(p.getId()) != null) {
			return	projectRepositoy.save(p);
		}

		throw new ProjectException();
	}

	@Override
	public Projects deleteProjectById(Long id) {
		logger.info("projectService-deleteProject");
		Projects p = getProjectById(id);
		if(p != null) {
			projectRepositoy.deleteById(id);
			return p;
		}
		return null;
	}

	@Override
	public ProjectDto toDto(Projects project) {
		ProjectDto dto = new ProjectDto();
		if(project != null) {
			dto.setLicenseName(project.getLicense().getBusinessName());
			dto.setName(project.getName());
			dto.setDescription(project.getDescription());
			dto.setStartDate(project.getStartDate());
			dto.setEndDate(project.getEndDate());
			dto.setTotalDays(project.getTotalDays());
		}
		return dto;
	}

}
