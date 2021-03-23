package net.agm.hydra.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
	
	private String licenseName;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private int totalDays;

}
