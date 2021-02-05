package net.agm.hydra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.agm.hydra.model.Updates;
@Repository
public interface UpdatesRopository extends JpaRepository<Updates,Long> {
	

	@Query("select u from Updates u join u.assigned a where a.userId = ? ")
	List<Updates> findAllByAssigned_UserId(Long userId);
    
	@Query("select u from Updates u join u.assigned a where a.taskId = ? ")
	List<Updates> findAllByAssigned_TaskId(Long taskId);
	@Query("select u from Updates u join u.assigned a where a.taskId = ? and a.userId = ?")
    List<Updates> findAllByAssigned_TaskId_UserId(Long taskId, Long userId);
}
