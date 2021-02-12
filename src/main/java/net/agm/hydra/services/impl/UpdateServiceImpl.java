package net.agm.hydra.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.agm.hydra.model.Updates;
import net.agm.hydra.repository.AssignedRepository;
import net.agm.hydra.repository.TasksRepository;
import net.agm.hydra.repository.UpdatesRopository;
import net.agm.hydra.repository.UsersRepository;
import net.agm.hydra.services.TasksService;
import net.agm.hydra.services.UpdateService;
import net.agm.hydra.services.UsersService;
@Service
public class UpdateServiceImpl implements UpdateService {


	@Autowired
	UpdatesRopository updatesRopository;

	@Autowired
	UsersService userService;

	@Autowired
	TasksService tasksService;

	@Autowired
	AssignedRepository assignedRepository;

	@Override
	public List<Updates> getAll() {
		return updatesRopository.findAll();
	}

	@Override
	public Updates addUpdates(Updates u) {
		if(u != null && u.getId() == null) {
			return updatesRopository.save(u);
		}
		throw new UpdateException();
	}

	@Override
	public List<Updates> getUpdatesOfUserById(Long userId) {
		List<Updates> updates = null;
		userService.getUserById(userId);
		updates = updatesRopository.findAllByAssigned_Users_Id(userId);
		return updates;
	}

	@Override
	public List<Updates> getUpdatesOfTasksById(Long taskId) {
		List<Updates> updates = null;
		tasksService.getTaskById(taskId);
		updatesRopository.findAllByAssigned_Tasks_Id(taskId);

		return updates ;
	}

	@Override
	public List<Updates> getUpdatesTaskByUserId(Long taskId, Long userId) {
		List<Updates> updates = null;
		userService.getUserById(userId);
		tasksService.getTaskById(taskId);
		if(assignedRepository.findAllByUsers_IdAndTasks_Id(userId, taskId) != null) {	
			updates =  updatesRopository.findAllByAssigned_Tasks_IdAndAssigned_Users_Id(taskId, userId);
		} else {
           throw new UpdateException(); 
		}
		return updates;
	}

}
