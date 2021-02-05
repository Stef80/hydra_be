package net.agm.hydra.services;

import java.util.List;

import net.agm.hydra.model.Users;

public interface UsersService {
	
	Users getUserById(Long id);
	
	List<Users> getUsers();
	
	Users newUser(Users u);
	
	Users updateUser(Users u);
	
	boolean deleteUserById(Long id);

}
