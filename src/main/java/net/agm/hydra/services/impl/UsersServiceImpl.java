package net.agm.hydra.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        	 return usersRepository.findById(id).orElse(null);
		return null;
	}

	@Override
	public List<Users> getUsers() {
		
		return usersRepository.findAll();
	}

	@Override
	public Users newUser(Users u) {
		return usersRepository.save(u);
	}

	@Override
	public Users updateUser(Users u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUserById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
