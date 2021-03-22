package net.agm.hydra.config.multitenant;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class TenantFilterAspect {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	  @Pointcut("execution (* org.hibernate.internal.SessionFactoryImpl.SessionBuilderImpl.openSession(..))")
	    public void openSession() {
	    }

	    @AfterReturning(pointcut = "openSession()", returning = "session")
	    public void afterOpenSession(Object session) {
	        if (session != null && Session.class.isInstance(session)) {
	            final Long tenantId = TenantContext.getTenantId();
                logger.info("afteropensession tenantId: " + tenantId);
 
	            if (tenantId != null) {
	                org.hibernate.Filter filter = ((Session) session).enableFilter("tenantFilter");
	                logger.info("afteropensession filter: " + filter.getName());
	                filter.setParameter("tenantId", tenantId);
	              
	            }
	        }
	    }

}
