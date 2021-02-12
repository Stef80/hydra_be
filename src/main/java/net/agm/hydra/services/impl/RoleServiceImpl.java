package net.agm.hydra.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.agm.hydra.datamodel.Role;
import net.agm.hydra.exception.RoleException;
import net.agm.hydra.model.Roles;
import net.agm.hydra.model.Users;
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

	@Override
	public Roles addRoletoUser(String email, String role) {
		Roles newRole = null;
		if(role != null && email != null) {
			Role trueRole = Role.valueOf(role.trim().toUpperCase());
			Users tmpUser = userService.getUserByMail(email);
			newRole = roleRepository.save(new Roles(tmpUser,trueRole));
			if(tmpUser.getRoleses().isEmpty() && newRole != null) {
				tmpUser.setActived(true);
				userService.updateUser(tmpUser);
			}
			}else {
				throw new RoleException();
			}
		return newRole;
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
       	
}
