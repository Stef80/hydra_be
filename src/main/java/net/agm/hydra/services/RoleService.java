package net.agm.hydra.services;

import java.util.List;

import net.agm.hydra.exception.RoleException;
import net.agm.hydra.model.Roles;
import net.agm.hydra.model.Users;

public interface RoleService {
	
	
   Roles addRoletoUser(Users user, String role) throws RoleException;
   
   List<Roles> getRolesFromUser(Long userId) throws RoleException;
   
   boolean deleteRoleFromUser(Long userId);
	
}
