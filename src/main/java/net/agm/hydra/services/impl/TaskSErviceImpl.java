package net.agm.hydra.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import net.agm.hydra.model.Tasks;
import net.agm.hydra.services.TasksService;
@Service
public class TaskSErviceImpl implements TasksService {

	@Override
	public Tasks getTaskById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tasks updateTaskById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tasks newTask(Tasks t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tasks> getTasksByProjectId(Long projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tasks> getTasksByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tasks> getTasksByUserAndProjectId(Long userId, Long projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean assignUserToTask(Long userId, Long tasksId) {
		// TODO Auto-generated method stub
		return false;
	}

}
