package net.agm.hydra.model;
// Generated 9-feb-2021 15.20.01 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users")
public class Users implements java.io.Serializable {

	private Long id;
	private String email;
	private String name;
	private String surname;
	private String password;
	private String workplace;
	private String expertiseArea;
	private Set<Assigned> assigneds = new HashSet<Assigned>(0);
	private Set<Roles> roleses = new HashSet<Roles>(0);

	public Users() {
	}

	public Users(Long id, String email, String name, String surname, String password, String workplace,
			String expertiseArea) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.workplace = workplace;
		this.expertiseArea = expertiseArea;
	}

	public Users(Long id, String email, String name, String surname, String password, String workplace,
			String expertiseArea, Set<Assigned> assigneds, Set<Roles> roleses) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.workplace = workplace;
		this.expertiseArea = expertiseArea;
		this.assigneds = assigneds;
		this.roleses = roleses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Column(name = "expertise_area", nullable = false)
	public String getExpertiseArea() {
		return this.expertiseArea;
	}

	public void setExpertiseArea(String expertiseArea) {
		this.expertiseArea = expertiseArea;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Assigned> getAssigneds() {
		return this.assigneds;
	}

	public void setAssigneds(Set<Assigned> assigneds) {
		this.assigneds = assigneds;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Roles> getRoleses() {
		return this.roleses;
	}

	public void setRoleses(Set<Roles> roleses) {
		this.roleses = roleses;
	}

}
