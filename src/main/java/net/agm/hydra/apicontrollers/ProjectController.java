package net.agm.hydra.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import net.agm.hydra.exception.ProjectException;
import net.agm.hydra.model.Projects;
import net.agm.hydra.services.ProjectsService;

@Controller
@RequestMapping("/api/project")
public class ProjectController {
	
	
	@Autowired
	ProjectsService projectService;
	
	@GetMapping
	public List<Projects> getAll(){
		return projectService.getAll();
	}
	
	
	@PostMapping
	public Projects newProject(@RequestBody Projects p) {
		if(p != null) {
			try {
				return projectService.newProject(p);
			}catch (ProjectException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("{id}")
	public Projects getProjectById(@PathVariable Long id) {
		Projects tmp = null;
		if(id > 0 && id != null) {
			try {
				tmp = projectService.getProjectById(id);
			}catch (ProjectException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return tmp;
	}
	
	@PutMapping
	public Projects updateProject(@RequestBody Projects p) {
		Projects tmp = null;
	try {
		tmp = projectService.updateProject(p);
	} catch (ProjectException e) {
		 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
	}
	 return tmp;
	}


}
