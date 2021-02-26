package net.agm.hydra.model;
// Generated 26-feb-2021 14.59.23 by Hibernate Tools 5.2.12.Final

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
import net.agm.hydra.datamodel.Role;

/**
 * Roles generated by hbm2java
 */
@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = { "role", "user_fk" }))
public class Roles implements java.io.Serializable {

	private Long id;
	private Users users;
	private Role role;

	public Roles() {
	}

	public Roles(Users users, Role role) {
		this.users = users;
		this.role = role;
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
	@JoinColumn(name = "user_fk", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "role", nullable = false, length = 18)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
