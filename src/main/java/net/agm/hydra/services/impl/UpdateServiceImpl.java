package net.agm.hydra.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.agm.hydra.exception.UpdateException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Assigned;
import net.agm.hydra.model.Updates;
import net.agm.hydra.model.dto.UpdatesDto;
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
	
	Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public List<Updates> getAll() {
		return updatesRopository.findAll();
	}

	@Override
	public Updates addUpdates(Map<String, Object> body) {
		Updates u = new Updates();
		
		if(body != null) {
			Long userId =((Integer) body.get("userId")).longValue();
			Long taskId =((Integer) body.get("taskId")).longValue();
			Assigned assigned = assignedRepository.findAllByUsers_IdAndTasks_Id(userId, taskId);
			logger.info("service-addUppdate body: " , body);

			Float hours =((Double) body.get("hours")).floatValue();
			if(assigned != null && hours != null) {
		
				u.setAssigned(assigned);
				u.setDateOfPublish(new Date());
				u.setHoursOfWorking(hours);
				
				logger.info("service-addUppdate update:" , u);

				u = updatesRopository.save(u);
				
			} else {
				throw new UpdateException();
			}
		}else {
			throw new UserNotFoundException();
		}
      return u;
	}

	
	@Override
	public List<Updates> getUpdatesOfUserById(Long userId) {
		List<Updates> updates = null;
		if(userId != null ) {
			userService.getUserById(userId);
			updates = updatesRopository.findAllByAssigned_Users_Id(userId);
		}else {
			throw new UserNotFoundException();
		}
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
	
	
	@Override
	public UpdatesDto toDto(Updates u) {
		UpdatesDto dto = new UpdatesDto();
		 dto.setUserEmail(u.getAssigned().getUsers().getEmail());
		 dto.setTaskName(u.getAssigned().getTasks().getTaskName());
		 dto.setDateOfPublish(u.getDateOfPublish());
	     dto.setHoursOfWorking(u.getHoursOfWorking());
		return dto;
	}

}
