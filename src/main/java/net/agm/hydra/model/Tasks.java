package net.agm.hydra.model;
// Generated 5-feb-2021 11.57.39 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tasks generated by hbm2java
 */
@Entity
@Table(name = "tasks")
public class Tasks implements java.io.Serializable {

	private long taskId;
	private Projects projects;
	private Date dateOfRegistation;
	private String state;
	private String taskName;
	private Byte totalWorked;
	private Set<Assigned> assigneds = new HashSet<Assigned>(0);

	public Tasks() {
	}

	public Tasks(long taskId, Projects projects, Date dateOfRegistation, String state, String taskName) {
		this.taskId = taskId;
		this.projects = projects;
		this.dateOfRegistation = dateOfRegistation;
		this.state = state;
		this.taskName = taskName;
	}

	public Tasks(long taskId, Projects projects, Date dateOfRegistation, String state, String taskName,
			Byte totalWorked, Set<Assigned> assigneds) {
		this.taskId = taskId;
		this.projects = projects;
		this.dateOfRegistation = dateOfRegistation;
		this.state = state;
		this.taskName = taskName;
		this.totalWorked = totalWorked;
		this.assigneds = assigneds;
	}

	@Id

	@Column(name = "task_id", unique = true, nullable = false)
	public long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	public Projects getProjects() {
		return this.projects;
	}

	public void setProjects(Projects projects) {
		this.projects = projects;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_registation", nullable = false, length = 13)
	public Date getDateOfRegistation() {
		return this.dateOfRegistation;
	}

	public void setDateOfRegistation(Date dateOfRegistation) {
		this.dateOfRegistation = dateOfRegistation;
	}

	@Column(name = "state", nullable = false, length = 20)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "task_name", nullable = false)
	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Column(name = "total_worked", precision = 2, scale = 0)
	public Byte getTotalWorked() {
		return this.totalWorked;
	}

	public void setTotalWorked(Byte totalWorked) {
		this.totalWorked = totalWorked;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tasks")
	public Set<Assigned> getAssigneds() {
		return this.assigneds;
	}

	public void setAssigneds(Set<Assigned> assigneds) {
		this.assigneds = assigneds;
	}

}
