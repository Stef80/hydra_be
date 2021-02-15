package net.agm.hydra.apicontrollers;

import java.util.List;
import java.util.Map;


import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.agm.hydra.exception.TaskException;
import net.agm.hydra.exception.UpdateException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Tasks;
import net.agm.hydra.model.Updates;
import net.agm.hydra.services.TasksService;
import net.agm.hydra.services.UpdateService;

@RestController
@RequestMapping("/api/updates")
public class UpdatesController {

	@Autowired
	UpdateService updateService;

	@Autowired
	TasksService taskService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping
	public List<Updates> getAll() {
		return updateService.getAll();
	}

	@PostMapping("/add")
	public Updates addUpdates(@RequestBody Map<String, Object> body) {
		Updates updates = null;
		try {
			updates = updateService.addUpdates(body);
			float hours = updates.getHoursOfWorking();
			Tasks task = updates.getAssigned().getTasks();
			logger.info("controller-addUppdate task:" +  task);
			if(task.getTotalWorked()!= null)
				task.setTotalWorked(task.getTotalWorked() + hours);
			else
				task.setTotalWorked(hours);	
			taskService.updateTask(task);

		} catch (UpdateException|TaskException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return updates;
	}

	@GetMapping("/user/{id}")
	public List<Updates> getAllByUserId(@PathVariable("id") Long id) {
		List<Updates> updatesList = null;
		try {
			updatesList = updateService.getUpdatesOfUserById(id);
		}catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

		return updatesList;
	}


	@GetMapping("/tasks/{id}")
	public List<Updates> getAllByTaskId(@PathVariable("id") Long id) {
		List<Updates> updatesList = null;
		try {
			updatesList = updateService.getUpdatesOfTasksById(id);
		}catch (TaskException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

		return updatesList;
	}


	@GetMapping("/user/{user_id}/task/{task_id}")
	public List<Updates> getAllUserAndTaskId(@PathVariable("user_id") Long userId, @PathVariable("task_id") Long taskId){
		List<Updates> updatesList = null;
		try {
			updatesList = updateService.getUpdatesTaskByUserId(taskId, userId);
		}catch (TaskException|UserNotFoundException|UpdateException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

		return updatesList;
	}



}
