package net.agm.hydra.model.dto;

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
	Role role;

	
		
}
