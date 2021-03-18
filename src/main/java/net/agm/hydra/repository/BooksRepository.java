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
	 
	 @Query("SELECT p from Books p WHERE start_date > :startDate  AND  bookable_fk = :bookableId")
	 List<Books>  findAllByStartAndBookables(Date startDate, Long bookableId);
	 
	 List<Books> findAllByUsers_id(Long id);
	 
	 @Query("SELECT p from Books p WHERE bookable_fk = :bookableId AND end_date > :startDate  AND start_date < :endDate" )
	 List<Books>  findIfFree(Date startDate, Date endDate, Long bookableId);
     
	 @Query("SELECT p from Books p WHERE bookable_fk = :bookableId AND EXTRACT(DAY FROM start_date) = :day")
	 List<Books> findByDayAndBookable(Long bookableId, Integer day);
	 
	 @Query("SELECT p from Books p WHERE EXTRACT(DAY FROM start_date) = :day")
	 List<Books> findByDay(Integer day);
	 
	 @Query("SELECT p from Books p WHERE bookables_fk = :bookableId AND EXTRACT(MONTH FROM start_date) = :month")
	 List<Books>  findByMonthAndBookable(Long bookableId, Integer month);
	 
	 @Query("SELECT p from Books p WHERE EXTRACT(MONTH FROM start_date) = :month")
	 List<Books>  findByMonth(Integer month);
	 
}
