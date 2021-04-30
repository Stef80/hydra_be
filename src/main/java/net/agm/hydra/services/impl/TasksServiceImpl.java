package net.agm.hydra.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.agm.hydra.exception.ProjectException;
import net.agm.hydra.exception.TaskException;
import net.agm.hydra.exception.UserException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Assigned;
import net.agm.hydra.model.Projects;
import net.agm.hydra.model.Tasks;
import net.agm.hydra.model.Users;
import net.agm.hydra.model.dto.TasksDto;
import net.agm.hydra.repository.AssignedRepository;
import net.agm.hydra.repository.ProjectsRepository;
import net.agm.hydra.repository.TasksRepository;
import net.agm.hydra.repository.UsersRepository;
import net.agm.hydra.services.LicenseService;
import net.agm.hydra.services.TasksService;
@Service
public class TasksServiceImpl  implements TasksService {



	@Autowired
	TasksRepository tasksRepositroy;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	ProjectsRepository projectsRepository;

	@Autowired
	AssignedRepository assignedRepository;
	
	@Autowired
	LicenseService licenseService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Override
	public List<Tasks> getAll() {
		logger.info("TaskService-getAll");
		return tasksRepositroy.findAll();
	}

	@Override
	public Tasks getTaskById(Long id) {
		logger.info("TaskService-getTaskById");
		return tasksRepositroy.findById(id).orElseThrow(TaskException::new);

	}

	@Override
	public Tasks updateTask(Tasks t , Long tId) {
		logger.info("TaskService-updateTask");
		if(t != null ) {
			if(tasksRepositroy.findById(tId).isPresent()) {
				t.setId(tId);
				return tasksRepositroy.save(t);
			} else {
				throw new TaskException();
			}
		}else {
			throw new TaskException();
		}	
	}

	@Override
	public Tasks newTask(Tasks t) {
		logger.info("task-newTask():" + t);
		if(t != null && t.getId() == null) {
			return tasksRepositroy.save(t);
		}
		throw new TaskException();
	}

	@Override
	public List<Tasks> getTasksByProjectId(Long projectId) {
		logger.info("TaskService-getTaskByProjectId");
		if(projectId != null && projectsRepository.findById(projectId).orElse(null) != null) {
			return tasksRepositroy.findByProjects_Id(projectId);
		}
		throw new ProjectException();
	}

	@Override
	public List<Tasks> getTasksByUserId(Long userId) {
		if(userId != null && usersRepository.findById(userId).orElse(null) != null) {
			logger.info("service-getTaskByUser-prima di assigned");
			List<Assigned> assignedTasks = assignedRepository.findAllByUsers_Id(userId);
			logger.info("service-getTaskByUser- assigned: "+ assignedTasks);
			List<Tasks> tasks = new ArrayList<>();
			for (Assigned assigned : assignedTasks) {
				tasks.add(assigned.getTasks());
			}
			return tasks;
		}
		throw new UserNotFoundException();
	}

	@Override
	public List<Tasks> getTasksByUserAndProjectId(Long userId, Long projectId) {
		logger.info("TaskService-getTasksByUserAndProjectId");
		List<Tasks> tasksList = null;
		if(userId != null && usersRepository.findById(userId).orElse(null) != null) {
			if(projectId != null && projectsRepository.findById(projectId).orElse(null)!= null) {
				tasksList = tasksRepositroy.findAllByProjects_IdAndAssigneds_Users_Id(projectId, userId);
			}else {
				throw new UserNotFoundException();
			}
		} else {
			throw new ProjectException();
		}

		return tasksList;
	}

	

	@Override
	public Tasks addTasksRevisioning(Long projectId, String taskName,Float hours, Long userId) {
		logger.info("TaskService-addTaskRevisioning");
		Tasks t = null;
		Tasks newTasks = null;
		if(projectId != null && taskName != null && userId != null) {
		  	t = tasksRepositroy.findByProjects_idAndTaskNameAnd(projectId, taskName).orElseThrow(TaskException::new);
		    newTasks = new Tasks();
		    newTasks.setProjects(t.getProjects());
		    newTasks.setDateOfRegistration(t.getDateOfRegistration());
		    newTasks.setStatus(t.getStatus());
		    newTasks.setTaskName(t.getTaskName());
		    newTasks.setLicense(t.getLicense());
			newTasks.setDateOfPublish((new Date()));
			newTasks.setHoursOfWorking(hours);
			if(t.getTotalWorked() == null) {
				newTasks.setTotalWorked(hours);
			}else {
				newTasks.setTotalWorked(t.getTotalWorked() + hours);
			}
			newTasks.setRevision(t.getRevision() + 1);
			newTasks = tasksRepositroy.save(newTasks);
			if(newTasks != null) {
				Users user = usersRepository.findById(userId).orElseThrow(UserException::new);
				Assigned ass = new Assigned( newTasks, user);
				assignedRepository.save(ass);
			}
		}
		return  newTasks;
	}

	@Override
	public TasksDto toDto(Tasks t) {
         TasksDto dto = new TasksDto();
         dto.setProjectId((t.getProjects().getId()));
         dto.setProjectName(t.getProjects().getName());
         dto.setTaskName(t.getTaskName());
         dto.setDateOfRegistration(t.getDateOfRegistration());
         dto.setStatus(t.getStatus());
         dto.setTotalWorked(t.getTotalWorked());
         dto.setDateOfPublish(t.getDateOfPublish());
         Float hours = t.getHoursOfWorking();
         if(hours != null)
         dto.setHoursOfWorking(hours);
        
		return dto;
	}
	
	@Override
	public Tasks fromDto(TasksDto d) {
		Tasks dto  = new Tasks();
		logger.info("service-fromdto taskDto: " + d);
		Projects project = projectsRepository.findById(d.getProjectId()).orElse(null);
		if(project != null) {
			dto.setProjects(project);
			dto.setTaskName(d.getTaskName());
			dto.setDateOfRegistration(d.getDateOfRegistration());
			dto.setDateOfPublish(d.getDateOfPublish());
			dto.setHoursOfWorking(d.getHoursOfWorking());
			logger.info("status " +  d.getStatus());
			dto.setStatus(d.getStatus());	
		}else {
			throw new ProjectException();
		}
		
		return dto;
	}

}
