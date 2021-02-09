package net.agm.hydra.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.agm.hydra.model.Updates;
import net.agm.hydra.repository.AssignedRepository;
import net.agm.hydra.repository.TasksRepository;
import net.agm.hydra.repository.UpdatesRopository;
import net.agm.hydra.repository.UsersRepository;
import net.agm.hydra.services.UpdateService;

public class UpdateServiceImpl implements UpdateService {
	
	
	@Autowired
	UpdatesRopository updatesRopository;
	
	@Autowired 
	UsersRepository userRepository;
	
	@Autowired
	TasksRepository tasksRepository;
	
	@Autowired
	AssignedRepository assignedRepository;

	@Override
	public List<Updates> getAll() {
	   return updatesRopository.findAll();
	}

	@Override
	public Updates addUpdates(Updates u) {
		if(u != null && u.getId() == 0) {
			return updatesRopository.save(u);
		}
		return null;
	}

	@Override
	public List<Updates> getUpdatesOfUserById(Long userId) {
	   if(userId > 0 && userRepository.findById(userId) != null) {
		   updatesRopository.findAllByAssigned_Users_Id(userId);
	   }
		return null;
	}

	@Override
	public List<Updates> getUpdatesOfTasksById(Long taskId) {
		if(taskId > 0 && tasksRepository.findById(taskId) != null) {
			return updatesRopository.findAllByAssigned_Tasks_Id(taskId);
		}
		return null;
	}

	@Override
	public List<Updates> getUpdatesTaskByUserId(Long taskId, Long userId) {
		if(taskId > 0 && tasksRepository.findById(taskId) != null
				&& userId > 0 && userRepository.findById(userId) != null) {
			if(assignedRepository.findAllByUsers_IdAndTasks_Id(userId, taskId) != null)
		        return updatesRopository.findAllByAssigned_Tasks_IdAndAssigned_Users_Id(taskId, userId);
		}
		return null;
	}

}
