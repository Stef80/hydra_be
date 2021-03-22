package net.agm.hydra.model.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.agm.hydra.datamodel.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TasksDto {
	
	
	private Long projectId;
	private String taskName;
	private Date dateOfRegistration;
	private Status status;
	private Float totalWorked;
	private Date dateOfPublish;
	private float hoursOfWorking;
	
	


}
