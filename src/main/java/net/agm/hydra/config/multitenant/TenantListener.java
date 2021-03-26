package net.agm.hydra.config.multitenant;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.agm.hydra.model.License;
import net.agm.hydra.repository.LicenseRepository;

@Component
public class TenantListener {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	private static LicenseRepository licenseRepository ;




	@PreUpdate
	@PreRemove
	@PrePersist
	public void setTenant(TenantAware entity) {
		logger.info("setTenant-license entity " + entity);
		final Long tenantId = TenantContext.getTenantId();
		logger.info("setTenant-service " + licenseRepository);
		License license = licenseRepository.findById(tenantId).orElse(null);
		if(license!= null && license.getEndDate().after(new Date())) {
		logger.info("setTenant-license " + license);
		entity.setLicense(license);
		}
	}
	
	
	@Autowired
	public void init(LicenseRepository licenseRepository) 
	{
	    TenantListener.licenseRepository = licenseRepository;
	    logger.info("Initializing with dependency ["+ licenseRepository +"]"); 
	}

}
