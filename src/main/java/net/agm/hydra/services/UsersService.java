package net.agm.hydra.services;

import java.util.List;

import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Users;
import net.agm.hydra.model.dto.UsersDto;

public interface UsersService {
	
	Users getUserById(Long id) throws UserNotFoundException;
	
	Users getUserByMail(String email) throws UserNotFoundException ;
	
	List<Users> getUsers();
	
	Users newUser(Users u);
	
	Users updateUser(Users u)throws UserNotFoundException;
	
	Users deleteUserByIdLogically(Long id);
	
	Users deleteUserByIdFisically(Long id);
	
	UsersDto toDto(Users user);

}
