package net.agm.hydra.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.stereotype.Service;

import net.agm.hydra.exception.BooksException;
import net.agm.hydra.model.Bookables;
import net.agm.hydra.model.Books;
import net.agm.hydra.model.Users;
import net.agm.hydra.model.dto.BooksDto;
import net.agm.hydra.repository.BooksRepository;
import net.agm.hydra.services.BookablesService;
import net.agm.hydra.services.BooksService;
import net.agm.hydra.services.UsersService;


@Service
public class BooksServiceImpl implements BooksService {

	
	@Autowired
	BooksRepository booksRepository;
	
	@Autowired
	BookablesService bookableService;
	
	@Autowired
	UsersService userService;
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Books> findAll() {
		return booksRepository.findAll();
	}

	@Override
	public BooksDto newBooks(Bookables bookable, Users user, Date startDate, Date endDate,String tenantId) {
		Books newBook = new Books();
		if(bookable != null && startDate != null && endDate != null) {
			if(isFree(bookable,startDate,endDate)) {
				newBook.setBookables(bookable);
				newBook.setUsers(user);
				newBook.setStartDate(startDate);
				newBook.setEndDate(endDate);
				newBook.setTenantId(tenantId);
				booksRepository.save(newBook);
			}else {
				throw new BooksException("Booking's not possible for this bookable");
			}	
		}

		return toDto(newBook);
	}

	@Override
	public Boolean isFree(Bookables bookable, Date startDate, Date endDate) {
		if(bookable != null && startDate != null && endDate != null) {
			List<Books> booksList = booksRepository.findIfFree(startDate, endDate, bookable.getId());
			log.info("booksService-isFree bookList: " + booksList);
			if(booksList.isEmpty()) 
				return true;
		}else {
			throw new BooksException();
		}
		return false;
	}

	@Override
	public List<BooksDto> getBooksOfBookable(Long bookableId) {
		List<BooksDto> listDto = new ArrayList<>();
		if(bookableId != null) {
			List<Books>  books = booksRepository.findAllByBookables_id(bookableId);
			for (Books books2 : books) {
				listDto.add(toDto(books2));
			}
		}else {
			throw new BooksException();
		}
		return listDto;
	}


	@Override
	public List<BooksDto> getBookOfBookableByDay(Long bookableId, Integer day) {
		List<BooksDto> dtoList = new ArrayList<>();
		if(bookableId > 0 && bookableId != null && day > 0 && day != null) {
			List<Books> booksList = booksRepository.findByDayAndBookable(bookableId, day);
			if(booksList != null) {
				for (Books books : booksList) {
					dtoList.add(toDto(books));
				}
			}
		}else {
			throw new BooksException();
		}
		return dtoList;

	}

	@Override
	public List<BooksDto> getBookOfBookableByMonth(Long bookableId, Integer month) {
		List<BooksDto> dtoList = new ArrayList<>();
		if(bookableId > 0 && bookableId != null && month > 0 && month != null) {
			List<Books> booksList = booksRepository.findByMonthAndBookable(bookableId, month);
			if(booksList != null) {
				for (Books books : booksList) {
					dtoList.add(toDto(books));
				}
			}
		}else {
			throw new BooksException();
		}
		return dtoList;
	}

	@Override
	public List<BooksDto> getBooksByDay(Integer day) {
		List<BooksDto> dtoList = new ArrayList<>();
		if( day > 0 && day != null) {
			List<Books> booksList = booksRepository.findByDay(day);
			if(booksList != null) {
				for (Books books : booksList) {
					dtoList.add(toDto(books));
				}
			}
		}else {
			throw new BooksException();
		}
		return dtoList;

	}

	@Override
	public List<BooksDto> getBooksByMonth(Integer month) {
		List<BooksDto> dtoList = new ArrayList<>();
		if( month > 0 && month != null) {
			List<Books> booksList = booksRepository.findByMonth(month);
			if(booksList != null) {
				for (Books books : booksList) {
					dtoList.add(toDto(books));
				}
			}
		}else {
			throw new BooksException();
		}
		return dtoList;
	}

	@Override
	public BooksDto updateBook(BooksDto book, Long id) throws BooksException {
         

		return null;
	}

	@Override
	public BooksDto deleteBook(Long id) throws BooksException {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public BooksDto toDto(Books book) {
		BooksDto dto = new BooksDto();
		if(book != null) {
			dto.setBookableName(book.getBookables().getName());
			dto.setUserEmail(book.getUsers().getEmail());
			dto.setStartDate(book.getStartDate());
			dto.setEndDate(book.getEndDate());
			dto.setTenantId(book.getTenantId());
		}

		return dto;
	}

	@Override
	public Books fromDto(BooksDto book) {
		Books from = new Books();
		if(book != null) {
			Bookables bookable = bookableService.getBookableByName(book.getBookableName());
			Users user = userService.getUserByMail(book.getUserEmail());
			from.setBookables(bookable);
			from.setUsers(user);
			from.setStartDate(book.getStartDate());
			from.setEndDate(book.getEndDate());
			from.setTenantId(book.getTenantId());
		}
		return null;
	}
	
	

}
