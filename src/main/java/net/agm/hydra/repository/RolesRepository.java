package net.agm.hydra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.agm.hydra.model.Roles;
@Repository
public interface RolesRepository extends JpaRepository<Roles, Long>{

}
