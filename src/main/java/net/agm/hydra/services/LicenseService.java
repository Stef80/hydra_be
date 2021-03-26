package net.agm.hydra.services;

import java.util.List;

import net.agm.hydra.exception.LicenseException;
import net.agm.hydra.model.License;

public interface LicenseService {
	
	
	public List<License> getAll();
	
	public License newLicense(License license);
	
	public License getLicenseById(Long id)throws LicenseException ;
	
	public License updateLicense(License license, Long id)throws LicenseException;
	
	public License deleteLicenseById(Long id)throws LicenseException;
	

}
