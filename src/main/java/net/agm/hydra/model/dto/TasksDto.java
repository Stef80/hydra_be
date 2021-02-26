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
	private Date dateOfRegistation;
	private Status status;
	private Float totalWorked;
	private Date dateOfPublish;
	private float hoursOfWorking;
	
	
	
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
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getDateOfRegistation() {
		return dateOfRegistation;
	}
	public void setDateOfRegistration(Date dateOfRegistation) {
		this.dateOfRegistation = dateOfRegistation;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Float getTotalWorked() {
		return totalWorked;
	}
	public void setTotalWorked(Float totalWorked) {
		this.totalWorked = totalWorked;
	}
	@Override
	public String toString() {
		return "TasksDto [projectId=" + projectId + ", taskName=" + taskName + ", dateOfRegistation="
				+ dateOfRegistation + ", status=" + status + ", totalWorked=" + totalWorked + "]";
	}
	
	
	

}
