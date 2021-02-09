package net.agm.hydra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.agm.hydra.model.Tasks;
@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {
	
	
	List<Tasks>  findByProjects_Id(Long id);
	
	List<Tasks> findAllByProjects_IdAndAssigneds_Users_Id(Long projectId, Long userId);

}
