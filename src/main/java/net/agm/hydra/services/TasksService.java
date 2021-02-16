package net.agm.hydra.services;

import java.util.List;

import net.agm.hydra.exception.ProjectException;
import net.agm.hydra.exception.TaskException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Assigned;
import net.agm.hydra.model.Tasks;
import net.agm.hydra.model.dto.TasksDto;

public interface TasksService {
	
	List<Tasks> getAll();
	
	Tasks getTaskById(Long id) throws TaskException;
	
	Tasks updateTask(Tasks t) throws TaskException;
	
	Tasks newTask(Tasks t);
	
	List<Tasks> getTasksByProjectId(Long projectId) throws ProjectException;
	
	List<Tasks> getTasksByUserId(Long userId) throws UserNotFoundException;
	
	List<Tasks> getTasksByUserAndProjectId(Long userId, Long projectId) throws UserNotFoundException,ProjectException;
	
	Assigned assignUserToTask(Long userId, Long tasksId ) throws UserNotFoundException,TaskException;
	
    TasksDto toDto(Tasks t);
    
    Tasks fromDto(TasksDto d);
}
