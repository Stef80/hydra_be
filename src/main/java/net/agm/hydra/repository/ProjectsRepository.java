package net.agm.hydra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.agm.hydra.model.Projects;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {

}
