package net.agm.hydra.apicontrollers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import net.agm.hydra.model.dto.BookableDto;
import net.agm.hydra.services.BookablesService;

@RestController
@RequestMapping("api/bookable")
public class BookableController {
	
	private final String TENANT_ID = "X-TENANT-ID";
	
	@Autowired
	BookablesService bookableService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@GetMapping
	public List<BookableDto> getAll() {
		logger.info("getAll()");
		List<BookableDto> dtoList = new ArrayList<>();
		List<Bookables> bookablesList = bookableService.getAll();
		for (Bookables bookables : bookablesList) {
		   dtoList.add(bookableService.toDto(bookables));	
		}
		return dtoList ;
	}
	
	
	@PostMapping("/addbookable")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public BookableDto newBookable(@RequestBody Map<String,String> bookable) {
		logger.info("newBookable ");
		Bookables newBookable = null;
		if(bookable != null) {
			logger.info("newBookable-map " + bookable);
			String name = bookable.get("name");
			String description = bookable.get("description");
			newBookable = bookableService.newBookable(name, description);
			logger.info("newBookable-newBookable " + newBookable);
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return bookableService.toDto(newBookable);
	}
	
	
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public BookableDto update(@RequestBody Bookables bookable,@PathVariable("id") Long id ) {
		logger.info("update-bookable " + bookable);
		Bookables updated = null;
		if(bookable != null && id != null && id > 0 ) {
			try {
				updated = bookableService.update(bookable, id);
				logger.info("update-updated " + updated);
			}catch (BooksException e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
			}	
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return bookableService.toDto(updated);
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public BookableDto deleteById(@PathVariable Long id) {
		logger.info("deleteById-id " + id);
		Bookables bookables = null;
		if(id != null && id > 0 ) {
			try {
				bookables = bookableService.deleteBookable(id);
				logger.info("deleteById-bookables " + bookables);

			} catch (BooksException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());			}
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return bookableService.toDto(bookables);
	}
	

}
