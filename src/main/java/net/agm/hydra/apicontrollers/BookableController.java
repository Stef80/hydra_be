package net.agm.hydra.apicontrollers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.agm.hydra.model.Bookables;
import net.agm.hydra.services.BookablesService;

@RestController
@RequestMapping("api/bookable")
public class BookableController {
	
	private final String TENANT_ID = "X-TENANT-ID";
	
	@Autowired
	BookablesService bookableService;
	
	@GetMapping
	public List<Bookables> getAll() {
		return bookableService.getAll();
	}
	
	
	@PostMapping("/addbookable")
	public Bookables newBookable(@RequestBody Map<String,String> bookable, @RequestHeader(value= TENANT_ID) String tenant) {
		Bookables newBookable = null;
		if(bookable != null) {
			String name = bookable.get("name");
			String description = bookable.get("description");
			newBookable = bookableService.newBookable(name, description, tenant);
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return newBookable;
	}
	
	
	@PutMapping("/update/{id}")
	public Bookables update(@RequestBody Bookables bookable,@PathVariable("id") Long id ) {
		Bookables updated = null;
		
		return updated;
	}

}
