package net.agm.hydra.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import net.agm.hydra.model.Assigned;
import net.agm.hydra.model.Roles;
import net.agm.hydra.model.Users;


public class CustomUserDetails extends Users implements UserDetails {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());


	public CustomUserDetails(Users u) {
		super(u.getEmail(), u.getName(),u.getSurname(),u.getPassword(),u.getWorkplace(), u.getExpertiseArea(),u.isActived(),u.getTenantId(), u.getRoleses(),u.getBookses(), u.getAssigneds());
		logger.info("userdetails " + u);
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authority = new ArrayList<>();
		getRoleses().stream().forEach(r -> authority.add(new SimpleGrantedAuthority("ROLE_"+ r.getRole())));
		logger.info("userdetails-getAuthorities authorities" + authority);
		return authority;
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
