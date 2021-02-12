package net.agm.hydra.apicontrollers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import net.agm.hydra.exception.ProjectException;
import net.agm.hydra.exception.TaskException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Assigned;
import net.agm.hydra.model.Tasks;
import net.agm.hydra.model.dto.TasksDto;
import net.agm.hydra.services.TasksService;

@Controller
@RequestMapping("/api/task")
public class TasksController {

	@Autowired
	TasksService taskService;
	
	
	@GetMapping
	public List<Tasks> getAll(){
		return taskService.getAll();
	}
	
	@PostMapping
	public Tasks newTask(Tasks t) {
		Tasks newTask= null;
		try {
			newTask = taskService.newTask(t);
		} catch (TaskException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return newTask;
	}
	
	
	@GetMapping("/{id}")
	public Tasks getTaskById(@PathVariable("id") Long id) {
		Tasks task = null;
		try {
			task = taskService.getTaskById(id);
		} catch (TaskException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return task;
	}
	
	@GetMapping("/user/{id]")
	public List<Tasks> getTaskByUserId(@PathVariable("id") Long id) {
	//	List<TasksDto> dtoList = new  ArrayList<>(); 
		//TODO : verificare quali valori sono necessari ritornare 
		List<Tasks> taskList = null;
		try {
			taskList = taskService.getTasksByUserId(id);
		} catch (TaskException|UserNotFoundException e) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
		return taskList;
	}
	
	
	
	@GetMapping("project/{id}")
	public List<Tasks> getTasksByProjectId(@PathVariable("id") Long id) {
		List<Tasks> tasksList = null;
		try {
			tasksList = taskService.getTasksByProjectId(id);
		} catch (ProjectException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
		return tasksList;
	}
	
	
	@GetMapping("user/{user_id}/project/{project_id}")
	public List<Tasks> getTasksByUserAndProject(@PathVariable("user_id") Long userId, @PathVariable("project_id") Long projectId) {
		List<Tasks> tasksList = null;
		try {
			tasksList = taskService.getTasksByUserAndProjectId(userId, projectId);
		} catch (ProjectException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
		return tasksList;
	}
	
	
	@PostMapping("/assign/{user_id}/{task_id}")
	public Assigned addUserToTask(@PathVariable("user_id") Long userId, @PathVariable("task_id") Long taskId) {
		Assigned newAssign = null;
		try {
			newAssign = taskService.assignUserToTask(userId, taskId);
		} catch (UserNotFoundException|TaskException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} 
		return newAssign;
	}
	
	
}
