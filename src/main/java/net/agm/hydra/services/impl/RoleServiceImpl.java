package net.agm.hydra.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.agm.hydra.datamodel.Role;
import net.agm.hydra.model.Roles;
import net.agm.hydra.model.Users;
import net.agm.hydra.repository.RolesRepository;
import net.agm.hydra.repository.UsersRepository;
import net.agm.hydra.services.RoleService;


@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RolesRepository roleRepository;
	
	@Autowired
	UsersRepository userRepositoy;

	@Override
	public Roles addRoletoUser(Users user, String role) {
		Role trueRole = Role.valueOf(role.trim().toUpperCase());
		if(trueRole != null && userRepositoy.findById(user.getId()) != null) {
			return roleRepository.save(new Roles(null,user,trueRole));
		}
		return null;
	}

	@Override
	public List<Roles> getRolesFromUser(Long userId) {
		return roleRepository.findAllByUsers_Id(userId) ;
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
