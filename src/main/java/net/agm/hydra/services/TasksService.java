package net.agm.hydra.services;

import java.util.List;

import net.agm.hydra.model.Tasks;

public interface TasksService {
	
	Tasks getTaskById(Long id);
	
	Tasks updateTask(Tasks t);
	
	Tasks newTask(Tasks t);
	
	List<Tasks> getTasksByProjectId(Long projectId);
	
	List<Tasks> getTasksByUserId(Long userId);
	
	List<Tasks> getTasksByUserAndProjectId(Long userId, Long projectId);
	
	boolean assignUserToTask(Long userId, Long tasksId );
	

}
