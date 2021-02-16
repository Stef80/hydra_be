package net.agm.hydra.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatesDto {
	
	
	private String userEmail;
	private String taskName;
	private Date dateOfPublish;
	private float hoursOfWorking;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getDateOfPublish() {
		return dateOfPublish;
	}
	public void setDateOfPublish(Date dateOfPublish) {
		this.dateOfPublish = dateOfPublish;
	}
	public float getHoursOfWorking() {
		return hoursOfWorking;
	}
	public void setHoursOfWorking(float hoursOfWorking) {
		this.hoursOfWorking = hoursOfWorking;
	}
	
	
	

}
