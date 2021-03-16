package net.agm.hydra.model;
// Generated 16-mar-2021 17.41.48 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import net.agm.hydra.datamodel.Status;
import net.agm.hydra.model.base.BaseEntity;

/**
 * Tasks generated by hbm2java
 */
@Entity
@Table(name = "tasks")
public class Tasks extends BaseEntity  {

	private Long id;
	private Projects projects;
	private String taskName;
	private Date dateOfRegistration;
	private Status status;
	private Float totalWorked;
	private Date dateOfPublish;
	private Float hoursOfWorking;
	private int revision;
	private String tenantId;
	private Set<Assigned> assigneds = new HashSet<Assigned>(0);

	public Tasks() {
	}

	public Tasks(Projects projects, String taskName, Date dateOfRegistration, Status status, int revision) {
		this.projects = projects;
		this.taskName = taskName;
		this.dateOfRegistration = dateOfRegistration;
		this.status = status;
		this.revision = revision;
	}

	public Tasks(Projects projects, String taskName, Date dateOfRegistration, Status status, Float totalWorked,
			Date dateOfPublish, Float hoursOfWorking, int revision, String tenantId, Set<Assigned> assigneds) {
		this.projects = projects;
		this.taskName = taskName;
		this.dateOfRegistration = dateOfRegistration;
		this.status = status;
		this.totalWorked = totalWorked;
		this.dateOfPublish = dateOfPublish;
		this.hoursOfWorking = hoursOfWorking;
		this.revision = revision;
		this.tenantId = tenantId;
		this.assigneds = assigneds;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_fk", nullable = false)
	public Projects getProjects() {
		return this.projects;
	}

	public void setProjects(Projects projects) {
		this.projects = projects;
	}

	@Column(name = "task_name", nullable = false)
	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_registration", nullable = false, length = 13)
	public Date getDateOfRegistration() {
		return this.dateOfRegistration;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	@Column(name = "status", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name = "total_worked", precision = 8, scale = 8)
	public Float getTotalWorked() {
		return this.totalWorked;
	}

	public void setTotalWorked(Float totalWorked) {
		this.totalWorked = totalWorked;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_publish", length = 29)
	public Date getDateOfPublish() {
		return this.dateOfPublish;
	}

	public void setDateOfPublish(Date dateOfPublish) {
		this.dateOfPublish = dateOfPublish;
	}

	@Column(name = "hours_of_working", precision = 8, scale = 8)
	public Float getHoursOfWorking() {
		return this.hoursOfWorking;
	}

	public void setHoursOfWorking(Float hoursOfWorking) {
		this.hoursOfWorking = hoursOfWorking;
	}

	@Column(name = "revision", nullable = false)
	public int getRevision() {
		return this.revision;
	}

	public void setRevision(int revision) {
		this.revision = revision;
	}

	@Column(name = "tenant_id")
	public String getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tasks")
	public Set<Assigned> getAssigneds() {
		return this.assigneds;
	}

	public void setAssigneds(Set<Assigned> assigneds) {
		this.assigneds = assigneds;
	}

}
