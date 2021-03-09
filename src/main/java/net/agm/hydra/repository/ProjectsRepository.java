package net.agm.hydra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.agm.hydra.model.Projects;
@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {

	 @Query("SELECT p from Projects p WHERE p.id = :id")
	    Optional<Projects> findById(Long id);
}
