package net.agm.hydra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.agm.hydra.model.Tasks;
@Repository
public interface TasksRepositroy extends JpaRepository<Tasks, Long> {

}
