package net.agm.hydra.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookableDto {
	
	private String licenseName;
	private String name;
	private String description;

}
