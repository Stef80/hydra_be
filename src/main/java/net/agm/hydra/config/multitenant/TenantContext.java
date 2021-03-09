package net.agm.hydra.config.multitenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenantContext {

	 private TenantContext() {}
	 
	

	    private static InheritableThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

	    public static void setTenantId(String tenantId) {
	        currentTenant.set(tenantId);
	    }

	    public static String getTenantId() {
	        return currentTenant.get();
	    }

	    public static void clear(){
	        currentTenant.remove();
	    }
	
}
