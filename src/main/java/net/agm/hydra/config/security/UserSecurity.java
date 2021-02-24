package net.agm.hydra.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import net.agm.hydra.model.Users;
import net.agm.hydra.services.UsersService;

@Component("userSecurity")
public class UserSecurity {
	
	
	@Autowired
	UsersService service;
	
	   public boolean hasUserId(Authentication authentication, Long userId) {
           String email = authentication.getName();
		   Users user =  service.getUserByMail(email);
		   return user.getId().equals(userId);
       }

}
