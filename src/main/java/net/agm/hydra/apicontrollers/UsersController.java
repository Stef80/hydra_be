package net.agm.hydra.apicontrollers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.web.servlet.headers.HeadersSecurityMarker;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.agm.hydra.datamodel.Activation;
import net.agm.hydra.datamodel.Role;
import net.agm.hydra.exception.RoleException;
import net.agm.hydra.exception.UserException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.License;
import net.agm.hydra.model.Roles;
import net.agm.hydra.model.Users;
import net.agm.hydra.model.dto.RolesDto;
import net.agm.hydra.model.dto.UsersDto;
import net.agm.hydra.services.LicenseService;
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
	
	@Autowired
	LicenseService licenseService;

	Logger logger = LoggerFactory.getLogger(this.getClass());


	@GetMapping
	public List<UsersDto> getAll() {
		List<UsersDto> dtoList = new ArrayList<>();
		List<Users> tmp = userService.getUsers();
		for (Users users : tmp) {
			dtoList.add(userService.toDto(users));
		}
		return dtoList ;
	}
	
	@GetMapping("/active")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<UsersDto> gellAllActive() {
		logger.info("Log: getAllActive()");
     	List<UsersDto> active = new ArrayList<>();
		List<Users> tmp = userService.getUsers();
		for (Users users : tmp) {
			if(users.getActived().equals(Activation.ACTIVE)) {
				active.add(userService.toDto(users));
			}
		}
		return active ;
	}

	
	@GetMapping("/cancelled")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<UsersDto> getAllCancelled() {
		logger.info("Log: getAllCancelled()");
		List<UsersDto> active = new ArrayList<>();
		List<Users> tmp = userService.getUsers();
		for (Users users : tmp) {
			if(!users.getActived().equals(Activation.ACTIVE)) {
				active.add(userService.toDto(users));
			}
		}
		return active ;
	}

	@PostMapping("/adduser")
	public UsersDto newUser(@RequestBody Users user) {
		Users newUser = null;
		logger.info("Log: newUser()");
		try {
			newUser = userService.newUser(user);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());		
		}
       
		return userService.toDto(newUser);
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
				throw new 
				ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
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
				logger.info("role list" + roleses);
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


	@GetMapping("/getbyid/{id}")
	public UsersDto getUserById(@PathVariable("id") Long id) {
		Users user = null;
		logger.info("Log: getUserById()" );
		try { 
			user = userService.getUserById(id);
		}catch (UserNotFoundException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
		return userService.toDto(user);
	}


	@GetMapping("/getbymail/{email}")
	public UsersDto getUserById(@PathVariable("email") String email) {
		logger.info("Log: getUserByEmail()" );
		Users user = null;
		try {
			user = userService.getUserByMail(email);
		}catch (UserNotFoundException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
		return userService.toDto(user);
	}




	@PutMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UsersDto updateUser(@RequestBody Users user) {
		logger.info("Log: updateUser()" );
		Users tmp = null;
		if(user != null) {
			tmp = userService.updateUser(user);
			
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return userService.toDto(tmp);
	}

	@PutMapping("/deletelogical/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UsersDto deleteUserlogicalById(@PathVariable("id") Long id) {
		logger.info("users-deleteUserlogicalById" );
		Users user = null;
		try {
			user = userService.deleteUserByIdLogically(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return userService.toDto(user);	
	}
  
	@DeleteMapping("/deletefisical/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UsersDto deleteUserFisicalById(@PathVariable("id") Long id) {
		logger.info("Users-deleteUserFisicalById()" );
		Users user = null;
		try {
			user = userService.deleteUserByIdFisically(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return userService.toDto(user);
	}

}
