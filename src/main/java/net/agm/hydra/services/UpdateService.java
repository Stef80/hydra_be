package net.agm.hydra.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import net.agm.hydra.exception.TaskException;
import net.agm.hydra.exception.UpdateException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Updates;

public interface UpdateService  {
	
	
	List<Updates> getAll();
	
	Updates addUpdates(Map<String, Object> body);
    
	List<Updates> getUpdatesOfUserById(Long userId) throws UserNotFoundException ;
	
	List<Updates> getUpdatesOfTasksById(Long taskId) throws TaskException;
	
	List<Updates> getUpdatesTaskByUserId(Long taskId, Long userId)throws UserNotFoundException, TaskException, UpdateException;
}