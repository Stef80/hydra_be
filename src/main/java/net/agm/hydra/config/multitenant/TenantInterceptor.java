package net.agm.hydra.config.multitenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import net.agm.hydra.services.LicenseService;

@Component
public class TenantInterceptor implements WebRequestInterceptor {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LicenseService licenseService;

	@Override
	public void preHandle(WebRequest request) throws Exception {
	     String tenantId = null;
	        if (request.getHeader("X-TENANT-ID") != null) {
	            tenantId = request.getHeader("X-TENANT-ID");
	            logger.info("preHandle tenantId " + tenantId);
	        } else {
	            tenantId = "0";
	        }
	        TenantContext.setTenantId(Long.parseLong(tenantId));

	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		TenantContext.clear();

	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {
		// TODO Auto-generated method stub

	}

}
