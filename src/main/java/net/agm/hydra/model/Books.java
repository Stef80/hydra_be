package net.agm.hydra.model;
// Generated 19-mar-2021 16.54.53 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import net.agm.hydra.model.base.BaseEntity;

/**
 * Books generated by hbm2java
 */
@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = { "bookable_fk", "start_date", "end_date" }))
public class Books extends BaseEntity{

	private Long id;
	private Bookables bookables;
	private License license;
	private Users users;
	private Date startDate;
	private Date endDate;

	public Books() {
	}

	public Books(Bookables bookables, Users users, Date startDate, Date endDate) {
		this.bookables = bookables;
		this.users = users;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Books(Bookables bookables, License license, Users users, Date startDate, Date endDate) {
		this.bookables = bookables;
		this.license = license;
		this.users = users;
		this.startDate = startDate;
		this.endDate = endDate;
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
	@JoinColumn(name = "bookable_fk", nullable = false)
	public Bookables getBookables() {
		return this.bookables;
	}

	public void setBookables(Bookables bookables) {
		this.bookables = bookables;
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
	@JoinColumn(name = "user_fk", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date", nullable = false, length = 29)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date", nullable = false, length = 29)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
