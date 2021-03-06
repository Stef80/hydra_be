package net.agm.hydra.apicontrollers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.agm.hydra.exception.ProjectException;
import net.agm.hydra.model.Projects;
import net.agm.hydra.model.dto.ProjectDto;
import net.agm.hydra.services.ProjectsService;

@RestController
@RequestMapping("api/project")
public class ProjectController {
	
	
	@Autowired
	ProjectsService projectService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@GetMapping
	public List<ProjectDto> getAll(){
		List<ProjectDto> dtoList = new ArrayList<>();
		List<Projects> list =projectService.getAll();
		logger.info("project-getAll()" + list.toString());
		for (Projects projects : list) {
			dtoList.add(projectService.toDto(projects));
		}
		return dtoList;
	}
	
	
	@PostMapping("/addproject")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ProjectDto newProject(@RequestBody Projects p) {
		logger.info("project-addproject: " + p);
		Projects newProjects = null;
		if(p != null) {
			try {
			   newProjects = projectService.newProject(p);
			}catch (ProjectException e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return projectService.toDto(newProjects);
	}
	
	
	@GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_WORKER','ROLE_ADMIN')")
	public ProjectDto getProjectById(@PathVariable("id") Long id) {
		logger.info("project-getProjectById: ");
		Projects tmp = null;
		if(id > 0 && id != null) {
			try {
				tmp = projectService.getProjectById(id);
			}catch (ProjectException e) {
				e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
			}
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return projectService.toDto(tmp);
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ProjectDto updateProject(@RequestBody Projects p) {
		logger.info("project-updateProject: " + p);
		Projects tmp = null;
	try {
		tmp = projectService.updateProject(p);
	} catch (ProjectException e) {
		 e.printStackTrace();
		 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
	}
	 return projectService.toDto(tmp);
	}


}
