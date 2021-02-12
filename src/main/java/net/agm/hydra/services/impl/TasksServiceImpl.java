package net.agm.hydra.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.agm.hydra.exception.ProjectException;
import net.agm.hydra.exception.TaskException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Assigned;
import net.agm.hydra.model.Tasks;
import net.agm.hydra.model.Users;
import net.agm.hydra.repository.AssignedRepository;
import net.agm.hydra.repository.ProjectsRepository;
import net.agm.hydra.repository.TasksRepository;
import net.agm.hydra.repository.UsersRepository;
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


	@Override
	public List<Tasks> getAll() {
		return tasksRepositroy.findAll();
	}

	@Override
	public Tasks getTaskById(Long id) {
		return tasksRepositroy.findById(id).orElseThrow(TaskException::new);

	}

	@Override
	public Tasks updateTask(Tasks t) {
		if(t != null ) {
			if(tasksRepositroy.findById(t.getId()).isPresent()) {
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
		if(t != null && t.getId() > 0) {
			return tasksRepositroy.save(t);
		}
		throw new TaskException();
	}

	@Override
	public List<Tasks> getTasksByProjectId(Long projectId) {
		if(projectId > 0 && projectsRepository.findById(projectId) != null) {
			return tasksRepositroy.findByProjects_Id(projectId);
		}
		throw new ProjectException();
	}

	@Override
	public List<Tasks> getTasksByUserId(Long userId) {
		if(userId > 0 && usersRepository.findById(userId) != null) {
			List<Assigned> assignedTasks = assignedRepository.findAllByUsers_Id(userId);
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
		List<Tasks> tasksList = null;
		if(userId > 0 && usersRepository.findById(userId) != null) {
			if(projectId > 0 && projectsRepository.findById(projectId)!= null) {
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
	public Assigned assignUserToTask(Long userId, Long taskId) {
		Assigned as = null;
		if(userId > 0  && taskId > 0 ) {
			Users user = usersRepository.findById(userId).orElse(null);
			if(  user != null ) {
				Tasks task = tasksRepositroy.findById(taskId).orElse(null);  
				if(task != null) {
				Assigned asi = new Assigned(task, user);
				    as =  assignedRepository.save(asi);
				}else {
					throw new UserNotFoundException();
				}
			} else {
				throw new TaskException();
			}
		}
	   return as;
	}

}
