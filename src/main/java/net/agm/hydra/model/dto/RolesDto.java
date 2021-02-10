package net.agm.hydra.model.dto;

import java.util.List;

import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.agm.hydra.datamodel.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RolesDto {
	
	
	String userEmail;
	List<Role> role;
	
	
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public List<Role> getRole() {
		return role;
	}
	public void setRole(List<Role> role) {
		this.role = role;
	}

		
}
