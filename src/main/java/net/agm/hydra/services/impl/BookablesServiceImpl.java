package net.agm.hydra.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.agm.hydra.exception.BooksException;
import net.agm.hydra.model.Bookables;
import net.agm.hydra.model.dto.BookableDto;
import net.agm.hydra.repository.BookablesRepository;
import net.agm.hydra.services.BookablesService;
import net.agm.hydra.services.LicenseService;

@Service
public class BookablesServiceImpl implements BookablesService{

	@Autowired
	BookablesRepository bookablesRepository;
	
	@Autowired
	LicenseService licenseService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Bookables> getAll() {
		return bookablesRepository.findAll();
	}

	@Override
	public Bookables newBookable(String name, String description) {
		Bookables newBookable = new Bookables();
		if(name != null && !name.isEmpty()) {
			newBookable.setName(name);
			newBookable.setDescription(description);
		
			newBookable = bookablesRepository.save(newBookable);
		}
		return newBookable;
	}

	@Override
	public Bookables getBookableById(Long id) {
		Bookables bookables = null;
		if(id != null && id > 0 ) {
			bookables = bookablesRepository.findById(id).orElseThrow(BooksException::new);
		}
		return bookables ;
	}

	@Override
	public Bookables update(Bookables bookable, Long id) {
	   Bookables update = null;
	   if(bookable != null && id != null && id > 0) {
		   bookablesRepository.findById(id).orElseThrow(BooksException:: new);
		   bookable.setId(id);
		   update =  bookablesRepository.save(bookable);
	   }
		return update;
	}

	@Override
	public Bookables deleteBookable(Long id) {
		Bookables deleting = null;
		if (id != null && id > 0) {
		  deleting =  bookablesRepository.findById(id).orElseThrow(BooksException:: new);
		  bookablesRepository.delete(deleting);
		}
		return deleting;
	}

	@Override
	public Bookables getBookableByName(String name) throws BooksException {
		Bookables bookable = null;
		if(name != null && !name.isEmpty()) {
			bookable = bookablesRepository.findByName(name).orElseThrow(BooksException::new);
			
		}
		return bookable;
	}

	@Override
	public BookableDto toDto(Bookables bookable) {
		BookableDto dto = new BookableDto();
		if(bookable != null) {
			logger.info("toDto-licenseName " + bookable.getLicense().getBusinessName());
			dto.setLicenseName(bookable.getLicense().getBusinessName());
			dto.setName(bookable.getName());
			dto.setDescription(bookable.getDescription());
		}

		return dto;
	}

}
