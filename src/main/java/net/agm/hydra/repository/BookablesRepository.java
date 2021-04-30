package net.agm.hydra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import net.agm.hydra.model.Bookables;

@Repository
public interface BookablesRepository extends JpaRepository<Bookables, Long> {

	
	 @Query("SELECT p from Bookables p WHERE p.id = :id")
	    Optional<Bookables> findById(Long id);
	 
	 
	 Optional<Bookables> findByName(String name);
}
