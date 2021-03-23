package net.agm.hydra.config.multitenant;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.hibernate.service.spi.InjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.agm.hydra.model.License;
import net.agm.hydra.services.LicenseService;


public class TenantListener {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LicenseService service ;
	
	

		@PreUpdate
	    @PreRemove
	    @PrePersist
	    public void setTenant(TenantAware entity) {
	    	  logger.info("setTenant-license entity " + entity);
	        final Long tenantId = TenantContext.getTenantId();
	        logger.info("setTenant-service " + service);
	        License license = service.getLicenseById(tenantId);
	        //BaseEntity tmp = new BaseEntity(tenantId) ;
	        logger.info("setTenant-license " + license);
	        entity.setLicense(license);
	    }

}
