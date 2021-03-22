package net.agm.hydra.config.multitenant;

public class TenantContext {

	 private TenantContext() {}
	 
	
	    private static InheritableThreadLocal<Long> currentTenant = new InheritableThreadLocal<>();

	    public static void setTenantId(Long tenantId) {
	    	System.out.println("tenantContext-setTenantId " + tenantId);
	        currentTenant.set(tenantId);
	    }

	    public static Long getTenantId() {
	        return currentTenant.get();
	    }

	    public static void clear(){
	        currentTenant.remove();
	    }
	
}
