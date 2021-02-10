package net.agm.hydra.apicontrollers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.agm.hydra.datamodel.Role;
import net.agm.hydra.exception.RoleException;
import net.agm.hydra.exception.UserException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Roles;
import net.agm.hydra.model.Users;
import net.agm.hydra.model.dto.RolesDto;
import net.agm.hydra.services.RoleService;
import net.agm.hydra.services.UsersService;
import net.bytebuddy.asm.Advice.Return;

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
		List<Users> tmp = userService.getUsers();
		return tmp ;
	}


	@PostMapping(value ="/addrole/{role}")
	public @ResponseBody Roles addRoleToUser(@RequestBody Users user, @PathVariable("role") String role) {
		logger.info("Log: addRoleToUser()" );
		if(user != null && !role.equals("") && role != null) {
			try {
				Roles newRole = roleService.addRoletoUser(user, role);
				logger.info("role added");
				return newRole;
			}catch (RoleException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping("/roles/{userId}")
	public RolesDto getRolesOfUser(@PathVariable("userId") Long userId) {
		RolesDto roleDto = new RolesDto();
		List<Role> roleList = new ArrayList<>();
		if(userId != null && userId > 0) {
			List<Roles> tmp = roleService.getRolesFromUser(userId);
			try {
				for (Roles roles : tmp) {
					roleList.add(roles.getRole());
				}
				roleDto.setUserEmail(tmp.get(0).getUsers().getEmail());
				roleDto.setRole(roleList);
			} catch (RoleException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
			}
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		}
		return roleDto;
	}


	@GetMapping("/{id}")
	public Users getUserById(@PathVariable("id") Long id) {
		Users user = null;
		logger.info("Log: getUserById()" );
		try {
			user = userService.getUserById(id);
		}catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
		return user;
	}
	
	
	@GetMapping("/{email}")
	public Users getUserById(@PathVariable("email") String email) {
		logger.info("Log: getUserByEmail()" );
		Users user = null;
		try {
			user = userService.getUserByMail(email);
		}catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
		return user;
	}

	@PostMapping("/adduser")
	public Users newUser(@RequestBody Users user) {
		Users tmp = null;
		logger.info("Log: newUser()"  );
		if(user != null) {
			try {
			tmp = userService.newUser(user);
			} catch (UserException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());		
				}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	   return user;
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

	@PutMapping("/delete")
	public Users deleteUserFromId(Long id) {
		Users tmp = null;
		if(id != null && id > 0 ) {
			tmp = userService.deleteUserById(id);
			if(tmp != null) {
				tmp.setStatus(0);
				return tmp;
			}else {
				throw new UserNotFoundException();
			}
		}

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}


}
