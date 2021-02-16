package net.agm.hydra.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import net.agm.hydra.model.Assigned;
import net.agm.hydra.model.Roles;
import net.agm.hydra.model.Users;

public class CustomUserDetails extends Users implements UserDetails {
	
	


	public CustomUserDetails(Users u) {
		super(u.getEmail(), u.getName(),u.getSurname(),u.getPassword(),u.getWorkplace(), u.getExpertiseArea(),u.isActived(),u.getAssigneds(), u.getRoleses());
		// TODO Auto-generated constructor stub
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authority = new ArrayList<>();
		getRoleses().stream().forEach(r -> authority.add(new SimpleGrantedAuthority(r.getRole().label)));
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
