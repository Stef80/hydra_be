package net.agm.hydra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.agm.hydra.model.License;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {

	
	 
}
