package net.agm.hydra.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.agm.hydra.datamodel.Activation;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {
	
	private String licenseName;
	private String email;
	private String name;
	private String surname;
	private String password;
	private String workplace;
	private String expertiseArea;
	private Activation actived;

}
