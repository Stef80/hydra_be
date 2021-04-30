package net.agm.hydra.model.base;

import java.io.Serializable;

import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.NoArgsConstructor;
import net.agm.hydra.config.multitenant.TenantAware;
import net.agm.hydra.config.multitenant.TenantListener;
import net.agm.hydra.model.License;
import net.agm.hydra.services.LicenseService;

@MappedSuperclass
@NoArgsConstructor
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "long")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
@EntityListeners(TenantListener.class)
public abstract class BaseEntity implements TenantAware, Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -66861807366942413L;

	@Autowired
	LicenseService licenseService;


	Logger logger = LoggerFactory.getLogger(this.getClass());

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenant_id")
	private License license;

 

	public BaseEntity(Long tenantId) {
		//	this.tenantId = id;
		this.license = licenseService.getLicenseById(tenantId);
	}


	
	


	


}
