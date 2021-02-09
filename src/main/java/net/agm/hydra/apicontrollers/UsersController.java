package net.agm.hydra.apicontrollers;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Roles;
import net.agm.hydra.model.Users;
import net.agm.hydra.model.dto.RolesDto;
import net.agm.hydra.services.RoleService;
import net.agm.hydra.services.UsersService;

@RestController
@RequestMapping("/api/user")
public class UsersController {

	@Autowired
	RoleService roleService;

	@Autowired 
	UsersService userService;

	Logger logger = LoggerFactory.getLogger(this.getClass());


	@GetMapping
	public List<Users> gellAll() {
		return userService.getUsers();
	}


	@PostMapping(value ="/addrole/{role}")
	public @ResponseBody Roles addRoleToUser(@RequestBody Users user, @PathVariable("role") String role) {
		logger.info("Log: addRoleToUser()" );
		Roles newRole = roleService.addRoletoUser(user, role);
		if(newRole != null) {
			logger.info("role added");
			return newRole;
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}


	@GetMapping("/roles")
	public List<RolesDto> getRolesOfUser(Long userId) {
		List<RolesDto> roleList = new ArrayList<>();
		if(userId != null && userId > 0) {
			List<Roles> tmp = roleService.getRolesFromUser(userId);
			if(tmp != null &&tmp.size() > 0 ) {
				for (Roles roles : tmp) {
					roleList.add(new RolesDto(roles.getUsers().getEmail(),roles.getRole()));
				}
			}else {
				throw new UserNotFoundException();
			}
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		}
		return roleList;
	}


	@GetMapping("/{id}")
	public Users getUserById(@PathVariable("id") Long id) {
		logger.info("Log: getUserById()" );
		Users user = userService.getUserById(id);
		if(user != null) {
			return user;
		}else{
			throw new UserNotFoundException();
		}

	}


	@PostMapping("/adduser")
	public Users newUser(@RequestBody Users user) {
		logger.info("Log: newUser()"  );
		if(user != null) {
			Users newUser = userService.newUser(user);
			if(newUser != null) {
				return newUser;
			}else {
				throw new UserNotFoundException();
			}
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}


	@PutMapping
	public Users updateUser(Users user) {
		logger.info("Log: updateUser()" );
		Users tmp = null;
		if(user != null) {
			tmp = userService.updateUser(user);
			if(tmp != null) {
				return tmp;
			}else {
				throw new UserNotFoundException();
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}


}
