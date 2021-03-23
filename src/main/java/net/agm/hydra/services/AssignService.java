package net.agm.hydra.services;

import net.agm.hydra.exception.TaskException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Assigned;
import net.agm.hydra.model.dto.AssignedDto;

public interface AssignService {
	
	
	Assigned assignUserToTask(Long userId, Long tasksId ) throws UserNotFoundException,TaskException;
	
	Assigned deleteUserToTask(Long userId, Long taskId) throws UserNotFoundException,TaskException;
	
	AssignedDto toDto(Assigned assigned);

}
