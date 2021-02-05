package net.agm.hydra.model;
// Generated 4-feb-2021 17.45.49 by Hibernate Tools 5.2.12.Final

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

/**
 * Assigned generated by hbm2java
 */
@Entity
@Table(name = "assigned")
public class Assigned implements java.io.Serializable {

	private long assignedId;
	private Tasks tasks;
	private Users users;
	private Set<Updates> updateses = new HashSet<Updates>(0);

	public Assigned() {
	}

	public Assigned(long assignedId, Tasks tasks, Users users) {
		this.assignedId = assignedId;
		this.tasks = tasks;
		this.users = users;
	}

	public Assigned(long assignedId, Tasks tasks, Users users, Set<Updates> updateses) {
		this.assignedId = assignedId;
		this.tasks = tasks;
		this.users = users;
		this.updateses = updateses;
	}

	@Id

	@Column(name = "assigned_id", unique = true, nullable = false)
	public long getAssignedId() {
		return this.assignedId;
	}

	public void setAssignedId(long assignedId) {
		this.assignedId = assignedId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id", nullable = false)
	public Tasks getTasks() {
		return this.tasks;
	}

	public void setTasks(Tasks tasks) {
		this.tasks = tasks;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "assigned")
	public Set<Updates> getUpdateses() {
		return this.updateses;
	}

	public void setUpdateses(Set<Updates> updateses) {
		this.updateses = updateses;
	}

}