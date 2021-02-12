package net.agm.hydra.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.agm.hydra.exception.UserException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Users;
import net.agm.hydra.repository.UsersRepository;
import net.agm.hydra.services.UsersService;

@Service
public class UsersServiceImpl implements UsersService {


	@Autowired
	UsersRepository usersRepository;

	@Override
	public Users getUserById(Long id) {
		if(id > 0 && id != null)
			return usersRepository.findById(id).orElseThrow(UserNotFoundException :: new);
		return null;
	}

	@Override
	public List<Users> getUsers() {
		return usersRepository.findAll();
	}



	@Override
	public Users getUserByMail(String email) {
		Users tmp = null;
		if(!email.equals("") && email != null) {
			tmp = usersRepository.findUsersByEmail(email);
			if(tmp != null) {
				return tmp;
			}else {
				throw new UserNotFoundException();
			}
		}else {
			return null;
		}
	}

	@Override
	public Users newUser(Users u) {
		//		String email = u.getEmail();
		//		Users tmp = getUserByMail(email);
		if(u != null) {
			return usersRepository.save(u);
		}else {
			throw new UserException();
		}
	}

	@Override
	public Users updateUser(Users u) {
		Users user = null;
		if(u != null ) {
			user = getUserById(u.getId());
			if(user != null ){
				user = usersRepository.save(u); 
			} else {
				throw new UserNotFoundException();
			}
		} else {
			throw new UserException();
		}
		return user;
	}

	@Override
	public Users deleteUserById(Long id) {
		Users deletedUser = null;
		if(id > 0 && id != null) {
			deletedUser = getUserById(id);
			deletedUser.setActived(false);;
			usersRepository.save(deletedUser);
			return deletedUser;
		}
		throw new UserException() ;
	}

}
