package net.agm.hydra.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.agm.hydra.exception.LicenseException;
import net.agm.hydra.model.License;
import net.agm.hydra.services.LicenseService;

@RestController
@RequestMapping("/license")
public class LicenseController {
	
	@Autowired
	LicenseService licenseService;
	
	
	@GetMapping
	public List<License> getAll() {
		return licenseService.getAll();
	}
	
	
	@PostMapping("/addlicense")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public License newLicense(@RequestBody License license) {
		License newLicense = null;
		try {
			newLicense = licenseService.newLicense(license);
		}catch (LicenseException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());		
		}
		return newLicense;
	}
	
	
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public License updateLicense(@RequestBody License license, @PathVariable(value ="id") Long id) {
		License update = null;
		try {
			update = licenseService.updateLicense(license, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());		
		}
		return update;
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public License deleteLicense(@PathVariable(value="id") Long id) {
		License update = null;
		try {
			update = licenseService.deleteLicenseById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());		
		}
		return update;
	}

}
