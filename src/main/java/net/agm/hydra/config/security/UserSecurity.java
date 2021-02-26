package net.agm.hydra.config.security;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import net.agm.hydra.model.Assigned;
import net.agm.hydra.model.Projects;
import net.agm.hydra.model.Tasks;
import net.agm.hydra.model.Users;
import net.agm.hydra.services.TasksService;
import net.agm.hydra.services.UsersService;

@Component("userSecurity")
public class UserSecurity {


	@Autowired
	UsersService userService;

	@Autowired
	TasksService taskService;

	public boolean hasUserId(Authentication authentication, Long userId) {
		String email = authentication.getName();
		Users user =  userService.getUserByMail(email);
		return user.getId().equals(userId);
	}

	public boolean hasUserIn(Authentication auteAuthentication, Long projectId) {
		List<Tasks> p = taskService.getTasksByProjectId(projectId);
		

		
		return false;
	}

}
