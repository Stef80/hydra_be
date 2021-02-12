package net.agm.hydra.services;

import java.util.List;

import net.agm.hydra.exception.RoleException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Roles;
import net.agm.hydra.model.Users;

public interface RoleService {
	
	
   Roles addRoletoUser(String email, String role) throws RoleException,UserNotFoundException;
   
   List<Roles> getRolesFromUser(Long userId) throws RoleException, UserNotFoundException;
   
   boolean deleteRoleFromUser(Long userId);
	
}
