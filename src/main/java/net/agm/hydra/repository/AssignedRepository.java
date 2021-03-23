package net.agm.hydra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.agm.hydra.model.Assigned;

@Repository
public interface AssignedRepository extends JpaRepository<Assigned, Long> {

	Assigned findAllByUsers_IdAndTasks_Id(Long userId,Long taskId);
	
	List<Assigned> findAllByUsers_Id(Long userId);
	
	 @Query("SELECT p from Assigned p WHERE p.id = :id")
	    Optional<Assigned> findById(Long id);
	 
	 Assigned findByUsers_idAndTasks_id(Long userId,Long tasksId);
}
