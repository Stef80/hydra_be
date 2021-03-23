package net.agm.hydra.apicontrollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.agm.hydra.exception.TaskException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Assigned;
import net.agm.hydra.model.dto.AssignedDto;
import net.agm.hydra.services.AssignService;

@RestController
@RequestMapping("api/assign")
public class AssignedController {
	
	@Autowired
	AssignService assignService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());


	@PostMapping("/add/{user_id}/{task_id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AssignedDto addUserToTask(@PathVariable("user_id") Long userId, @PathVariable("task_id") Long taskId) {
		logger.info("assignedController-addUserToTask");
		Assigned newAssign = null;
		try {
			newAssign = assignService.assignUserToTask(userId, taskId);
		} catch (UserNotFoundException|TaskException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} 
		return assignService.toDto(newAssign);
	}
	
	@DeleteMapping("/delete/{userId}/{taskId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AssignedDto deleteUserToTask(@PathVariable("user_id") Long userId, @PathVariable("task_id") Long taskId) {
		logger.info("assignedController-deleteUserToTask");
		Assigned assigned = null;
		try {
			assigned = assignService.deleteUserToTask(userId, taskId);
			} catch (UserNotFoundException|TaskException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} 
		
		return assignService.toDto(assigned);
	}
	
	

}
