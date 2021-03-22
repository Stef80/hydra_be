package net.agm.hydra.services.impl;

import java.util.List;

import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.agm.hydra.exception.LicenseException;
import net.agm.hydra.model.License;
import net.agm.hydra.repository.LicenseRepository;
import net.agm.hydra.services.LicenseService;

@Service
public class LicenseServiceImpl implements LicenseService {
	
	@Autowired
	LicenseRepository licenseRepository;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<License> getAll() {
		return licenseRepository.findAll();
	}

	@Override
	public License newLicense(License license) {
		License newLicense = null;
		if(license != null) {
			newLicense = licenseRepository.save(license);
		}
		return newLicense;
	}

	@Override

	public License getLicenseById(Long id) {
		License license = null;
		logger.info("getLicense-id " +id);
		if(id != null && id > 0) {
			logger.info("getLicense-licenseRepository " + licenseRepository);
			license = licenseRepository.findById(id).orElseThrow(LicenseException::new);
		}
		logger.info("getLicense-license" + license);
		return license;
	}

}
