package net.agm.hydra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.agm.hydra.model.Updates;
@Repository
public interface UpdatesRopository extends JpaRepository<Updates,Long> {
	

	
	List<Updates> findAllByAssigned_Users_Id(Long userId);
    
	
	List<Updates> findAllByAssigned_Tasks_Id(Long taskId);
	
    List<Updates> findAllByAssigned_Tasks_IdAndAssigned_Users_Id(Long taskId, Long userId);
}
