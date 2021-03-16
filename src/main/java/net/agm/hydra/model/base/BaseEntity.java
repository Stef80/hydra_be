package net.agm.hydra.model.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.agm.hydra.config.multitenant.TenantAware;
import net.agm.hydra.config.multitenant.TenantListener;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "string")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
@EntityListeners(TenantListener.class)
public abstract class BaseEntity implements TenantAware, Serializable{

	  @Column(name = "tenant_id")
	    private String tenantId;
	  
	  public BaseEntity() {}

	    public BaseEntity(String tenantId) {
	        this.tenantId = tenantId;
	    }

	
}
