package net.agm.hydra.model;
// Generated 4-feb-2021 17.45.49 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users")
public class Users implements java.io.Serializable {

	private long userId;
	private String email;
	private String name;
	private String surname;
	private String password;
	private String workplace;
	private String expetiseArea;
	private Set<Assigned> assigneds = new HashSet<Assigned>(0);
	private Set<Roles> roleses = new HashSet<Roles>(0);

	public Users() {
	}

	public Users(long userId, String email, String name, String surname, String password, String workplace,
			String expetiseArea) {
		this.userId = userId;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.workplace = workplace;
		this.expetiseArea = expetiseArea;
	}

	public Users(long userId, String email, String name, String surname, String password, String workplace,
			String expetiseArea, Set<Assigned> assigneds, Set<Roles> roleses) {
		this.userId = userId;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.workplace = workplace;
		this.expetiseArea = expetiseArea;
		this.assigneds = assigneds;
		this.roleses = roleses;
	}

	@Id

	@Column(name = "user_id", unique = true, nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "surname", nullable = false)
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "workplace", nullable = false)
	public String getWorkplace() {
		return this.workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	@Column(name = "expetise_area", nullable = false)
	public String getExpetiseArea() {
		return this.expetiseArea;
	}

	public void setExpetiseArea(String expetiseArea) {
		this.expetiseArea = expetiseArea;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Assigned> getAssigneds() {
		return this.assigneds;
	}

	public void setAssigneds(Set<Assigned> assigneds) {
		this.assigneds = assigneds;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Roles> getRoleses() {
		return this.roleses;
	}

	public void setRoleses(Set<Roles> roleses) {
		this.roleses = roleses;
	}

}
