package net.agm.hydra.services.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.agm.hydra.datamodel.Activation;
import net.agm.hydra.datamodel.Role;
import net.agm.hydra.exception.UserException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Users;
import net.agm.hydra.model.dto.UsersDto;
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
		UsersDto userDto = null;
		if(u != null ) {
			user = usersRepository.findById(u.getId()).orElse(null);
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
	public Users deleteUserByIdLogically(Long id) {
		logger.info("UserService-deleteUserById");
		Users deletedUser = null;
		if(id > 0 && id != null) {
			deletedUser = getUserById(id);
			deletedUser.setActived(Activation.INACTIVE);
			usersRepository.save(deletedUser);
			return deletedUser;
		}
		throw new UserException() ;
	}

	@Override
	public Users deleteUserByIdFisically(Long id) {
		logger.info("UserService-deleteUserById");
		Users deletedUser = null;
		if(id > 0 && id != null) {
			deletedUser = getUserById(id);
			usersRepository.delete(deletedUser);
		}
		return deletedUser;
	}

	@Override
	public UsersDto toDto(Users user) {
		UsersDto dto = new UsersDto();
		if(user != null) {
			dto.setLicenseName(user.getLicense().getBusinessName());
			dto.setEmail(user.getEmail());
			dto.setName(user.getName());
			dto.setSurname(user.getSurname());
			dto.setPassword(user.getPassword());
			dto.setWorkplace(user.getWorkplace());
			dto.setExpertiseArea(user.getExpertiseArea());
			dto.setActived(user.getActived());
		}
		return dto;
	}
	
	

}
