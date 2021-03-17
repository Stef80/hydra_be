package net.agm.hydra.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.agm.hydra.exception.BooksException;
import net.agm.hydra.model.Bookables;
import net.agm.hydra.model.Books;
import net.agm.hydra.model.Users;
import net.agm.hydra.model.dto.BooksDto;
import net.agm.hydra.repository.BooksRepository;
import net.agm.hydra.services.BooksService;


@Service
public class BooksServiceImpl implements BooksService {
	
	@Autowired
	BooksRepository booksRepository;

	@Override
	public List<Books> findAll() {
		return booksRepository.findAll();
	}

	@Override
	public BooksDto newBooks(Bookables bookable, Users user, Date startDate, Date endDate) {
		Books newBook = new Books();
		if(bookable != null && startDate != null && endDate != null) {
			if(isFree(bookable,startDate,endDate)) {
				newBook.setBookables(bookable);
				newBook.setUsers(user);
				newBook.setStartDate(startDate);
				newBook.setEndDate(endDate);
				booksRepository.save(newBook);
			}else {
				throw new BooksException("Books not possible for this bookable");
			}	
		}
		
		return toDto(newBook);
	}

	@Override
	public Boolean isFree(Bookables bookable, Date startDate, Date endDate) {
		if(bookable != null && startDate != null && endDate != null) {
			List<Books> booksList = booksRepository.findIfFree(startDate, endDate, bookable.getId());
			if(booksList.isEmpty()) 
				return true;
		}else {
			throw new BooksException();
		}
		return false;
	}

	@Override
	public List<BooksDto> getBooksOfBookable(Bookables bookable) {
		List<BooksDto> listDto = new ArrayList<>();
		if(bookable != null) {
			List<Books>  books = booksRepository.findAllByBookables_id(bookable.getId());
			for (Books books2 : books) {
				listDto.add(toDto(books2));
			}
		}else {
			throw new BooksException();
		}
		return listDto;
	}

	@Override
	public BooksDto toDto(Books book) {
       BooksDto dto = new BooksDto();
       if(book != null) {
    	   dto.setBookableName(book.getBookables().getName());
    	   dto.setUserEmail(book.getUsers().getEmail());
    	   dto.setStartDate(book.getStartDate());
    	   dto.setEndDate(book.getEndDate());
       }

		return dto;
	}

}
