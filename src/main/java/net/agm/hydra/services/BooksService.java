package net.agm.hydra.services;

import java.util.Date;
import java.util.List;

import net.agm.hydra.exception.BooksException;
import net.agm.hydra.model.Bookables;
import net.agm.hydra.model.Books;
import net.agm.hydra.model.Users;
import net.agm.hydra.model.dto.BooksDto;

public interface BooksService {
	
	
	List<Books> findAll();
	
	BooksDto newBooks(Bookables bookable,Users user, Date starDate, Date endDate)throws BooksException;
	
	Boolean isFree(Bookables bookable, Date startDate, Date endDate)throws BooksException;
	
	List<BooksDto> getBooksOfBookable(Bookables bookable);
	
	BooksDto toDto(Books book);
	
	

}
