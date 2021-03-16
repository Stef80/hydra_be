package net.agm.hydra.model;
// Generated 16-mar-2021 17.41.48 by Hibernate Tools 5.2.12.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
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
public class Assigned extends BaseEntity  {

	private Long id;
	private Tasks tasks;
	private Users users;
	private String tenantId;

	public Assigned() {
	}

	public Assigned(Tasks tasks, Users users) {
		this.tasks = tasks;
		this.users = users;
	}

	public Assigned(Tasks tasks, Users users, String tenantId) {
		this.tasks = tasks;
		this.users = users;
		this.tenantId = tenantId;
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

	@Column(name = "tenant_id")
	public String getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}
