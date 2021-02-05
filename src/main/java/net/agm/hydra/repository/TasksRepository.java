package net.agm.hydra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.agm.hydra.model.Tasks;
@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {
	
	List<Tasks> findByUserId(Long id);
	List<Tasks>  findByProjectId(Long id);
	List<Tasks> findByProjectIdAndUserId(Long projectId, Long userId);

}
