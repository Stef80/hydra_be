package net.agm.hydra.services;

import java.util.List;

import net.agm.hydra.model.Roles;
import net.agm.hydra.model.Users;

public interface RoleService {
	
	
   Roles addRoletoUser(Users user, String role);
   
   List<Roles> getRolesFromUser(Long userId);
   
   boolean deleteRoleFromUser(Long userId);
	
}
