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
	
	BooksDto newBooks(Bookables bookable,Users user, Date starDate, Date endDate, String tenantId)throws BooksException;
	
	BooksDto updateBook(BooksDto book , Long id)throws BooksException;
	
	BooksDto deleteBook(Long id)throws BooksException;
	
	Boolean isFree(Bookables bookable, Date startDate, Date endDate)throws BooksException;
	
	List<BooksDto> getBooksOfBookable(Long bookableId)throws BooksException;
	
	List<BooksDto> getBookOfBookableByDay(Long bookableId, Integer day)throws BooksException;
	
	List<BooksDto> getBookOfBookableByMonth(Long bookableId, Integer month)throws BooksException;
	
	List<BooksDto> getBooksByDay( Integer day)throws BooksException;
	
	List<BooksDto> getBooksByMonth( Integer Month)throws BooksException;
	
	BooksDto toDto(Books book);
	
	Books fromDto(BooksDto book);
	
	

}
