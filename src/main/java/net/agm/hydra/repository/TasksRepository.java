package net.agm.hydra.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.agm.hydra.model.Tasks;
@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {
	
	
	List<Tasks>  findByProjects_Id(Long id);
	
	List<Tasks> findAllByProjects_IdAndAssigneds_Users_Id(Long projectId, Long userId);
	
	@Query("SELECT t FROM Tasks t where project_fk = :projectId and task_name = :taskName and revision = (Select MAX(t.revision) from t where project_fk = :projectId and task_name = :taskName)")
	Optional<Tasks> findByProjects_idAndTaskNameAnd(@Param("projectId") Long projectId,@Param("taskName") String taskName);
     
	 @Query("SELECT p from Tasks p WHERE p.id = :id")
	    Optional<Tasks> findById(Long id);
}
