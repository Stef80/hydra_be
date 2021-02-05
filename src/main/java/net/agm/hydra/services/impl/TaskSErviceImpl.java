package net.agm.hydra.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.agm.hydra.model.Assigned;
import net.agm.hydra.model.Tasks;
import net.agm.hydra.model.Users;
import net.agm.hydra.repository.AssignedRepository;
import net.agm.hydra.repository.ProjectsRepository;
import net.agm.hydra.repository.TasksRepository;
import net.agm.hydra.repository.UsersRepository;
import net.agm.hydra.services.TasksService;
@Service
public class TaskSErviceImpl implements TasksService {

	@Autowired
	TasksRepository tasksRepositroy;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	ProjectsRepository projectsRepository;

	@Autowired
	AssignedRepository assignedRepository;

	@Override
	public Tasks getTaskById(Long id) {
		if(id > 0 && id != null) {
			return tasksRepositroy.findById(id).orElse(new Tasks());
		}
		return null;
	}

	@Override
	public Tasks updateTask(Tasks t) {
		if(t != null && getTaskById(t.getTaskId()) != null) {
			return tasksRepositroy.save(t);
		}
		return null;
	}

	@Override
	public Tasks newTask(Tasks t) {
		if(t != null && t.getTaskId() > 0) {
			return tasksRepositroy.save(t);
		}
		return null;
	}

	@Override
	public List<Tasks> getTasksByProjectId(Long projectId) {
		if(projectId > 0 && projectsRepository.findById(projectId) != null) {
			return tasksRepositroy.findByProjectId(projectId);
		}
		return null;
	}

	@Override
	public List<Tasks> getTasksByUserId(Long userId) {
		if(userId >0 && usersRepository.findById(userId) != null) {
			return tasksRepositroy.findByUserId(userId);
		}
		return null;
	}

	@Override
	public List<Tasks> getTasksByUserAndProjectId(Long userId, Long projectId) {
		if(userId > 0 && projectId > 0 && 
				projectsRepository.findById(projectId)!= null &&
				usersRepository.findById(userId) != null) {
			return tasksRepositroy.findByProjectIdAndUserId(projectId, userId);
		}

		return null;
	}

	@Override
	public boolean assignUserToTask(Long userId, Long taskId) {
		if(userId > 0  && taskId > 0 ) {
			Users user = usersRepository.findById(userId).orElse(null);
			Tasks task = getTaskById(taskId);
			if(  user != null && task != null) {
				Assigned as = new Assigned(0L, task, user);
				assignedRepository.save(as);
				return true;
			}
		}
		return false;
	}

}
