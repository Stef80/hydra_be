package net.agm.hydra.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import net.agm.hydra.exception.TaskException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Updates;
import net.agm.hydra.services.impl.UpdateException;

public interface UpdateService  {
	
	
	List<Updates> getAll();
	
	Updates addUpdates(Updates u);
    
	List<Updates> getUpdatesOfUserById(Long userId) throws UserNotFoundException ;
	
	List<Updates> getUpdatesOfTasksById(Long taskId) throws TaskException;
	
	List<Updates> getUpdatesTaskByUserId(Long taskId, Long userId)throws UserNotFoundException, TaskException, UpdateException;
}