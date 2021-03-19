package net.agm.hydra.model.dto;

import java.util.Date;

public class BooksDto {
	
	private String userEmail;
	private String bookableName;
	private Date startDate;
	private Date endDate;
	private String tenantId;
	
	public BooksDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public BooksDto(String userEmail, String bookableName, Date startDate, Date endDate,String tenantId) {
		
		this.userEmail = userEmail;
		this.bookableName = bookableName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.tenantId = tenantId;
	}




	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getBookableName() {
		return bookableName;
	}

	public void setBookableName(String bookableName) {
		this.bookableName = bookableName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}




	public String getTenantId() {
		return tenantId;
	}




	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	

}
