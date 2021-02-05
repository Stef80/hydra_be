package net.agm.hydra.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import net.agm.hydra.model.Updates;

public interface UpdateService  {
	
	
	List<Updates> getAll();
	
	Updates addUpdates(Updates u);
    
	List<Updates> getUpdatesOfUserById(Long userId);
	
	List<Updates> getUpdatesOfTasksById(Long taskId);
	
	List<Updates> getUpdatesTaskByUserId(Long taskId, Long userId);
}