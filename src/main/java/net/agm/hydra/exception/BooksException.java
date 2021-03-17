package net.agm.hydra.exception;

public class BooksException extends RuntimeException {

	public BooksException() {
         this("no bookable found for this book");

	}

	public BooksException(String message) {
		super(message);
		
	}
	


}
