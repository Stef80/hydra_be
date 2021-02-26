package net.agm.hydra.apicontrollers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@GetMapping("/active")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Users> gellAllActive() {
		logger.info("Log: getAllActive()");
     	List<Users> active = new ArrayList<>();
		List<Users> tmp = userService.getUsers();
		for (Users users : tmp) {
			if(users.isActived()) {
				active.add(users);
			}
		}
		return active ;
	}

	
	@GetMapping("/cancelled")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Users> getAllCancelled() {
		logger.info("Log: getAllCancelled()");
		List<Users> active = new ArrayList<>();
		List<Users> tmp = userService.getUsers();
		for (Users users : tmp) {
			if(!users.isActived()) {
				active.add(users);
			}
		}
		return active ;
	}

	@PostMapping("/adduser")
	public Users newUser(@RequestBody Users user) {
		Users newUser = null;
		logger.info("Log: newUser()");
		try {
			newUser = userService.newUser(user);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());		
		}
       
		return newUser;
	}



	@PostMapping("/email/{email}/addrole/{role}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public  RolesDto addRoleToUser(@PathVariable("email") String email, @PathVariable("role") String role) {
		logger.info("Log: addRoleToUser()" );
		RolesDto newRole = null;
		
		if( email != null && !role.equals("") && role != null) {
			try {
			    newRole = roleService.addRoletoUser(email, role);
				logger.info("role added");
			}catch (RoleException e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}catch (UserNotFoundException e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
			}
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return newRole;
	}


	@GetMapping("/roles/{userId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or @userSecurity.hasUserId(authentication,#userId)")
	public RolesDto getRolesOfUser(@PathVariable("userId") Long userId) {
		logger.info("Log: getRolesOfUser()");
		RolesDto roleDto = null;
		List<Role> roleList = new ArrayList<>();
		if(userId != null && userId > 0) {
			try {
				List<Roles> roleses = roleService.getRolesFromUser(userId);
				roleDto = roleService.toDto(roleses);
			
			} catch (RoleException|UserNotFoundException e) {
				e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
		return user;
	}




	@PutMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Users updateUser(@RequestBody Users user) {
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

	@PutMapping("/delete/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Users deleteUserFromId(@PathVariable Long id) {
		logger.info("Log: deleteUsersFromId()" );
		Users user = null;
		try {
			user = userService.deleteUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return user;	
	}


}
