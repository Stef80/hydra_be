package net.agm.hydra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.agm.hydra.model.Tasks;

public interface TasksRepositroy extends JpaRepository<Tasks, Long> {

}
