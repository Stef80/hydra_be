package net.agm.hydra.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import net.agm.hydra.datamodel.Role;
import net.agm.hydra.exception.RoleException;
import net.agm.hydra.model.Roles;
import net.agm.hydra.model.Users;
import net.agm.hydra.model.dto.RolesDto;
import net.agm.hydra.repository.RolesRepository;
import net.agm.hydra.repository.UsersRepository;
import net.agm.hydra.services.RoleService;
import net.agm.hydra.services.UsersService;


@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RolesRepository roleRepository;

	@Autowired
	UsersService userService;
	
	@Autowired
	UserDetailsServiceImpl userDatailservice;
	

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public RolesDto addRoletoUser(String email, String role) {
		Roles newRole = null;
		RolesDto  dto = null;
		if(role != null && email != null && Role.valueOf(role.toUpperCase()) != null) {
			
			Role trueRole = Role.valueOf(role.trim().toUpperCase());
			
			Users tmpUser = userService.getUserByMail(email);
			logger.info("addRoleToUser-usermail: " + tmpUser );
			if(!tmpUser.isActived() ) {
				logger.info("addRoleToUser-updateUser: " );
				tmpUser.setActived(true);
				tmpUser = userService.updateUser(tmpUser);
				logger.info("addRoleToUser-User Updated : " + tmpUser );
			}
			
		    newRole = roleRepository.save(new Roles(tmpUser,trueRole));
		    tmpUser.getRoleses().add(newRole);    
		    List<Roles> rList = tmpUser.getRoleses().parallelStream().collect(Collectors.toList());
		    
			dto = toDto(rList);
		}else {
			throw new RoleException();
		}
		return dto;
	}

	@Override
	public List<Roles> getRolesFromUser(Long userId) {
		List<Roles> rolesList = null;
		userService.getUserById(userId);
		rolesList = roleRepository.findAllByUsers_Id(userId);
		if(rolesList.size() > 0) {
			return rolesList;
		} else {
			throw new RoleException("No roles founds");
		}
	}

	@Override
	public boolean deleteRoleFromUser(Long userId) {  
		if (userId != null && userId >=0){
			try {
				roleRepository.deleteRolesByUsers_Id(userId);
				return true;
			}catch (RuntimeException e){
				//simulo il return false dal mock
				return false;
			}
		}
		return false;
	}

	@Override
	public RolesDto toDto(List<Roles> roleses) {	
		RolesDto roleDto = new RolesDto();
		List<Role> roleList = new ArrayList<>();
		for (Roles roles : roleses ) {
			roleList.add(roles.getRole());
		}
		roleDto.setUserEmail(roleses.get(0).getUsers().getEmail());
		roleDto.setRole(roleList);
		return roleDto;
	}
}
