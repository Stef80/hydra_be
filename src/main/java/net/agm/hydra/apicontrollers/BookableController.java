package net.agm.hydra.apicontrollers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.agm.hydra.exception.BooksException;
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
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Bookables newBookable(@RequestBody Map<String,String> bookable, @RequestHeader(value= TENANT_ID) Long tenant) {
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
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Bookables update(@RequestBody Bookables bookable,@PathVariable("id") Long id ) {
		Bookables updated = null;
		if(bookable != null && id != null && id > 0 ) {
			try {
				updated = bookableService.update(bookable, id);
			}catch (BooksException e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
			}	
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return updated;
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Bookables deleteById(@PathVariable Long id) {
		Bookables bookables = null;
		if(id != null && id > 0 ) {
			try {
				bookables = bookableService.deleteBookable(id);
			} catch (BooksException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());			}
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return bookables;
	}
	

}
