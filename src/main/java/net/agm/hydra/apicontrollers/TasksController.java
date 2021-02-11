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

import net.agm.hydra.exception.TaskException;
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
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return task;
	}
	
	@GetMapping("/user/{id]")
	public List<TasksDto> getTaskByUserId(@PathVariable("id") Long id) {
		List<TasksDto> dtoList = new  ArrayList<>();
		List<Tasks> taksList = null;
		
		return dtoList;
	}
	
	
	
}
