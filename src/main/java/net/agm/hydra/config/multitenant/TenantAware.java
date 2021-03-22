package net.agm.hydra.config.multitenant;

import net.agm.hydra.model.License;

public interface TenantAware {

	
	void setLicense(License tenantId);
}
