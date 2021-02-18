package net.agm.hydra.services.impl;

import java.util.Set;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.agm.hydra.config.CustomUserDetails;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Roles;
import net.agm.hydra.model.Users;
import net.agm.hydra.repository.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	Logger log = LoggerFactory.getLogger(this.getClass());


	@Autowired
	UsersRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = userRepository.findUsersByEmail(username);
		Hibernate.initialize(user.getRoleses());
		if (user == null)
			throw new UsernameNotFoundException("Username "+ username + " not found" );
		log.debug("loggato utente: "+ user);
	
		return new CustomUserDetails(user);
	}

}
