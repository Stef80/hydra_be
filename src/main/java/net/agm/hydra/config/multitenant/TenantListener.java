package net.agm.hydra.config.multitenant;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class TenantListener {
	
	    @PreUpdate
	    @PreRemove
	    @PrePersist
	    public void setTenant(TenantAware entity) {
	        final String tenantId = TenantContext.getTenantId();
	        entity.setTenantId(tenantId);
	    }

}
