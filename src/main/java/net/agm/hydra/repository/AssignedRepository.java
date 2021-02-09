package net.agm.hydra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.agm.hydra.model.Assigned;

public interface AssignedRepository extends JpaRepository<Assigned, Long> {

	Assigned findAllByUsers_IdAndTasks_Id(Long userId,Long taskId);
	
	List<Assigned> findAllByUsers_Id(Long userId);
	

	
}
