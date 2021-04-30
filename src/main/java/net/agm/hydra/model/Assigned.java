package net.agm.hydra.model;
// Generated 19-mar-2021 16.54.53 by Hibernate Tools 5.2.12.Final

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.agm.hydra.model.base.BaseEntity;

/**
 * Assigned generated by hbm2java
 */
@Entity
@Table(name = "assigned", uniqueConstraints = @UniqueConstraint(columnNames = { "user_fk", "task_fk" }))
public class Assigned extends BaseEntity {

	private Long id;
	private License license;
	private Tasks tasks;
	private Users users;

	public Assigned() {
	}

	public Assigned(Tasks tasks, Users users) {
		this.tasks = tasks;
		this.users = users;
	}

	public Assigned(License license, Tasks tasks, Users users) {
		this.license = license;
		this.tasks = tasks;
		this.users = users;
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
	@JoinColumn(name = "tenant_id")
	public License getLicense() {
		return this.license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_fk", nullable = false)
	public Tasks getTasks() {
		return this.tasks;
	}

	public void setTasks(Tasks tasks) {
		this.tasks = tasks;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_fk", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	

	

}
