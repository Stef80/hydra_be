package net.agm.hydra.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignedDto {
	
	UsersDto usesr;
	TasksDto task;

}
