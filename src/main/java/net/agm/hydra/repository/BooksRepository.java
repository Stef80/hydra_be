package net.agm.hydra.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.agm.hydra.model.Bookables;
import net.agm.hydra.model.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {
	
	 @Query("SELECT p from Books p WHERE p.id = :id")
	    Optional<Books> findById(Long id);
	 
	 List<Books>  findAllByBookables_id(Long id);
	 
	 @Query("SELECT p from Books p WHERE start_date < :startDate  AND  bookables_fk = :bookableId")
	 List<Books>  findAllByStartAndBookables(Date startDate, Long bookableId);

}
