package net.agm.hydra.services;

import java.util.List;

import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Users;

public interface UsersService {
	
	Users getUserById(Long id) throws UserNotFoundException;
	
	Users getUserByMail(String email) throws UserNotFoundException ;
	
	List<Users> getUsers();
	
	Users newUser(Users u);
	
	Users updateUser(Users u)throws UserNotFoundException;
	
	Users deleteUserById(Long id);

}
