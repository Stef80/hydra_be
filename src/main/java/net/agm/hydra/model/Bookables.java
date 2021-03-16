package net.agm.hydra.model;
// Generated 16-mar-2021 17.41.48 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.agm.hydra.model.base.BaseEntity;

/**
 * Bookables generated by hbm2java
 */
@Entity
@Table(name = "bookables")
public class Bookables extends BaseEntity  {

	private Long id;
	private String name;
	private String description;
	private String tenantId;
	private Set<Books> bookses = new HashSet<Books>(0);

	public Bookables() {
	}

	public Bookables(Long id, String name, String tenantId) {
		this.id = id;
		this.name = name;
		this.tenantId = tenantId;
	}

	public Bookables(Long id, String name, String description, String tenantId, Set<Books> bookses) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.tenantId = tenantId;
		this.bookses = bookses;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "tenant_id", nullable = false)
	public String getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookables")
	public Set<Books> getBookses() {
		return this.bookses;
	}

	public void setBookses(Set<Books> bookses) {
		this.bookses = bookses;
	}

}
