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


@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RolesRepository roleRepository;
	
	@Autowired
	UsersRepository userRepositoy;

	@Override
	public Roles addRoletoUser(Users user, String role) {
		Role trueRole = Role.valueOf(role.trim().toUpperCase());
		Users tmp =  userRepositoy.findById(user.getId()).orElse(null);
		if(trueRole != null && tmp != null) {
			List<Role> roleList = tmp.getRoleses().stream()
					.map(r -> r.getRole())
					.filter(r -> r.equals(trueRole))
					.collect(Collectors.toList());
			if(roleList.isEmpty()) {
			return roleRepository.save(new Roles(null,user,trueRole));
			}else {
				
				throw new RoleException();
			}
		}
		return null;
	}

	@Override
	public List<Roles> getRolesFromUser(Long userId) {
		List<Roles> rolesList = null;
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
