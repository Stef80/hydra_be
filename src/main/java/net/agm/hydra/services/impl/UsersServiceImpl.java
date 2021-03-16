package net.agm.hydra.services.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.agm.hydra.datamodel.Role;
import net.agm.hydra.exception.UserException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Users;
import net.agm.hydra.repository.UsersRepository;
import net.agm.hydra.services.EmailService;
import net.agm.hydra.services.UsersService;
import net.agm.hydra.utils.EmailSender;

@Service
public class UsersServiceImpl implements UsersService {


	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	EmailSender sender;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Override
	public Users getUserById(Long id) {
		logger.info("UserService-getUserById");
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
		logger.info("UserService-getUserByMail");
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
		logger.info("UserService-newUser");
		Users user = null;
		List<Users> userList = null;
		String password = u.getPassword();
		u.setPassword(new BCryptPasswordEncoder().encode(password));
		if(u != null) {
			user = usersRepository.save(u);
		if(user != null) {
			userList =	usersRepository.findAllByRoleses_role(Role.ADMIN);
			logger.info("userService-newUser- userlist: " + userList);
			final String body = "New user "+ u.getEmail()+" registered\nplease add role to confirm his registration! ";
			for (Users users : userList) {
				logger.info("userService-sendMail");
				sender.sendMessage(users.getEmail(),"new subscription", body);
			}
		}else {
			throw new UserException();
		}
		}
		return user;
	}

	@Override
	public Users updateUser(Users u) {
		logger.info("UserService-updateUser");
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
		logger.info("UserService-deleteUserById");
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
