package net.agm.hydra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.agm.hydra.model.Projects;
@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {

}
