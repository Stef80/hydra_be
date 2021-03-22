package net.agm.hydra.apicontrollers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.agm.hydra.exception.BooksException;
import net.agm.hydra.exception.UserNotFoundException;
import net.agm.hydra.model.Bookables;
import net.agm.hydra.model.Books;
import net.agm.hydra.model.Users;
import net.agm.hydra.model.dto.BooksDto;
import net.agm.hydra.services.BookablesService;
import net.agm.hydra.services.BooksService;
import net.agm.hydra.services.UsersService;

@RestController
@RequestMapping("api/book")
public class BooksController {
	
	private final String TENANT_ID = "X-TENANT-ID";
	
	@Autowired
	BooksService booksService;
	
	@Autowired
	UsersService userService;
	
	@Autowired
	BookablesService bookableService;
	
	
	@GetMapping
	public List<Books> getAll() {
		return booksService.findAll();
	}
	
	
	@PostMapping("/addbook")
	public BooksDto newBooks(@RequestBody Map<String, Object> books, @RequestHeader(value= TENANT_ID) Long tenantId, Authentication auth) {
		BooksDto dto = null;
		if(books != null) {
			Long bookableId =  Long.valueOf((Integer)books.get("bookable"));
			Date startDate = Timestamp.valueOf((String)books.get("start_date"));
			Date endDate = Timestamp.valueOf((String)books.get("end_date"));
			try {
				Users user = userService.getUserByMail(auth.getName());
				Bookables bookable = bookableService.getBookableById(bookableId);
				dto = booksService.newBooks(bookable, user, startDate, endDate,tenantId);
			} catch (UserNotFoundException e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}catch(BooksException e){
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
			
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return dto;
				
	}
	
	@GetMapping("/getbybookable/{bookable_id}")
	public List<BooksDto> getByBookable(@PathVariable("bookable_id") Long id) {
		List<BooksDto> dtoList = null;
		try {	
			dtoList = booksService.getBooksOfBookable(id);
		} catch (BooksException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return dtoList;
	}
	
	@GetMapping("/getbooksofbookableday/{bookable_id}/{day}")
	public List<BooksDto> getBooksOfBookableByDay(@PathVariable("bookable_id")Long bookableId,@PathVariable("day") Integer day) {
		List<BooksDto> dtoList = null;
		try {
			dtoList = booksService.getBookOfBookableByDay(bookableId, day);
		} catch (BooksException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return dtoList;
	}
	
	
	@GetMapping("/getbooksofbookablemonth/{bookable_id}/{month}")
	public List<BooksDto> getBooksOfBookableByMonth(@PathVariable("bookable_id")Long bookableId,@PathVariable("month") Integer month) {
		List<BooksDto> dtoList = null;
		try {
			dtoList = booksService.getBookOfBookableByMonth(bookableId, month);
		} catch (BooksException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return dtoList;
	}
	
	@GetMapping("/getbooksbyday/{day}")
	public List<BooksDto> getBooksOfBookableByDay(@PathVariable("day") Integer day) {
		List<BooksDto> dtoList = null;
		try {
			dtoList = booksService.getBooksByDay(day);
		} catch (BooksException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return dtoList;
	}
	

	@GetMapping("/getbooksbymonth/{month}")
	public List<BooksDto> getBooksOfBookableByMonth(@PathVariable("month") Integer month) {
		List<BooksDto> dtoList = null;
		try {
			dtoList = booksService.getBooksByMonth(month);
		} catch (BooksException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return dtoList;
	}
	
}
