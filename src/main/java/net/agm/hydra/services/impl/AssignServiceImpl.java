package net.agm.hydra.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.agm.hydra.exception.TaskException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Assigned;
import net.agm.hydra.model.Tasks;
import net.agm.hydra.model.Users;
import net.agm.hydra.model.dto.AssignedDto;
import net.agm.hydra.repository.AssignedRepository;
import net.agm.hydra.services.AssignService;
import net.agm.hydra.services.TasksService;
import net.agm.hydra.services.UsersService;

@Service
public class AssignServiceImpl implements AssignService {

	@Autowired
	AssignedRepository assignedRepository;

	@Autowired
	UsersService userService;

	@Autowired
	TasksService tasksService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Override
	public Assigned assignUserToTask(Long userId, Long taskId) {
		logger.info("TaskService-assignUserToTask");
		Assigned as = null;
		if(userId > 0  && taskId > 0 ) {
			Users user = userService.getUserById(userId);
			Tasks task = tasksService.getTaskById(taskId);  
			Assigned asi = new Assigned(task, user);
			as =  assignedRepository.save(asi);

		}
		return as;
	}


	@Override
	public Assigned deleteUserToTask(Long userId, Long taskId) throws UserNotFoundException, TaskException {
		logger.info("deleteUserToTask()");
		Assigned assigned = null;
		if(userId > 0  && taskId > 0 ) {
			assigned = assignedRepository.findByUsers_idAndTasks_id(userId, taskId);
			if(assigned != null)
				assignedRepository.delete(assigned);
		}
		return assigned;
	}

	@Override
	public AssignedDto toDto(Assigned assigned) {
		logger.info("toDto-assigned " + assigned);
		AssignedDto dto = new AssignedDto();
		dto.setTask(tasksService.toDto(assigned.getTasks()));
		dto.setUsesr(userService.toDto(assigned.getUsers()));
		return dto;
	}

}
