package net.agm.hydra.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import net.agm.hydra.model.Updates;
import net.agm.hydra.services.UpdateService;
import net.agm.hydra.services.impl.UpdateException;

@Controller
@RequestMapping("/api/updates")
public class UpdatesController {
	
    @Autowired
    UpdateService updateService;
    
    
	@GetMapping
	public List<Updates> getAll() {
		return updateService.getAll();
	}
	
	@PostMapping("/add")
	public Updates addUpdates(Updates up) {
		Updates updates = null;
		try {
			updates = updateService.addUpdates(up);
		//	double hours = updates.getHoursOfWorking();
		} catch (UpdateException e) {
			
		}
		return updates;
	}

}
