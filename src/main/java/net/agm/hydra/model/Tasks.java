package net.agm.hydra.model;
// Generated 4-feb-2021 10.50.46 by Hibernate Tools 5.2.12.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	private Users users;
	private Date dateOfRegistation;
	private String state;
	private BigDecimal totalWorked;
	private Updates updates;

	public Tasks() {
	}

	public Tasks(long taskId, Projects projects, Users users, Date dateOfRegistation, String state) {
		this.taskId = taskId;
		this.projects = projects;
		this.users = users;
		this.dateOfRegistation = dateOfRegistation;
		this.state = state;
	}

	public Tasks(long taskId, Projects projects, Users users, Date dateOfRegistation, String state,
			BigDecimal totalWorked, Updates updates) {
		this.taskId = taskId;
		this.projects = projects;
		this.users = users;
		this.dateOfRegistation = dateOfRegistation;
		this.state = state;
		this.totalWorked = totalWorked;
		this.updates = updates;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
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

	@Column(name = "total_worked", precision = 2)
	public BigDecimal getTotalWorked() {
		return this.totalWorked;
	}

	public void setTotalWorked(BigDecimal totalWorked) {
		this.totalWorked = totalWorked;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "tasks")
	public Updates getUpdates() {
		return this.updates;
	}

	public void setUpdates(Updates updates) {
		this.updates = updates;
	}

}
