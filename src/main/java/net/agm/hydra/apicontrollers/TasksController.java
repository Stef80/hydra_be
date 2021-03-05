package net.agm.hydra.apicontrollers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PreRemove;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.agm.hydra.exception.ProjectException;
import net.agm.hydra.exception.TaskException;
import net.agm.hydra.exception.UserException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Assigned;
import net.agm.hydra.model.Tasks;
import net.agm.hydra.model.dto.TasksDto;
import net.agm.hydra.services.TasksService;
import net.agm.hydra.services.UsersService;

@RestController
@RequestMapping("/api/task")
public class TasksController {

	@Autowired
	TasksService taskService;
	
	@Autowired
	UsersService userService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping
	public List<Tasks> getAll(){
		return taskService.getAll();
	}
	
	@PostMapping("/addtask")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Tasks newTask(@RequestBody TasksDto t) {
	logger.info("Log newTask taskDto:" + t);
		Tasks newTask= taskService.fromDto(t);
		try {
			newTask = taskService.newTask(newTask);
		} catch (TaskException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return newTask;
	}
	
	
	@PostMapping("/update/{projectid}")
	@PreAuthorize("hasRole('ROLE_WORKER') ")
	public Tasks updateTask(@RequestBody Tasks t, @PathVariable("projectid") Long projectId, Authentication auth) {
		Tasks newTask = null;
		try {
		Long userId = userService.getUserByMail(auth.getName()).getId();
	      newTask = taskService.addTasksRevisioning(projectId,t.getTaskName(), t.getHoursOfWorking(), userId);
			
		}catch (UserException|TaskException e) {
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
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return task;
	}
	
	@GetMapping("/user/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or @userSecurity.hasUserId(authentication, #id)")
	public List<TasksDto> getTaskByUserId(@PathVariable("id") Long id) {
	    List<TasksDto> dtoList = new  ArrayList<>();  
		List<Tasks> taskList = null;
		try {
			taskList = taskService.getTasksByUserId(id);
			for (Tasks tasks : taskList) {
				dtoList.add(taskService.toDto(tasks));
			}
			logger.info("task-getTasksByUserId():" + taskList);
		} catch (TaskException|UserNotFoundException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
		return dtoList;
	}
	
	
	
	@GetMapping("project/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or @userSecurity.hasUserIn(authentication, #id)")
	public List<Tasks> getTasksByProjectId(@PathVariable("id") Long id) {
		List<Tasks> tasksList = null;
		try {
			tasksList = taskService.getTasksByProjectId(id);
		} catch (ProjectException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
		return tasksList;
	}
	
	
	@GetMapping("user/{user_id}/project/{project_id}")
	public List<Tasks> getTasksByUserAndProject(@PathVariable("user_id") Long userId, @PathVariable("project_id") Long projectId) {
		List<Tasks> tasksList = null;
		try {
			tasksList = taskService.getTasksByUserAndProjectId(userId, projectId);
		} catch (ProjectException|UserNotFoundException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
		return tasksList;
	}
	
	
	@PostMapping("/assign/{user_id}/{task_id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Assigned addUserToTask(@PathVariable("user_id") Long userId, @PathVariable("task_id") Long taskId) {
		Assigned newAssign = null;
		try {
			newAssign = taskService.assignUserToTask(userId, taskId);
		} catch (UserNotFoundException|TaskException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} 
		return newAssign;
	}
	
	
}
