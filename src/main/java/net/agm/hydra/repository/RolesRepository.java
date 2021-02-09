package net.agm.hydra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.agm.hydra.datamodel.Role;
import net.agm.hydra.model.Roles;
@Repository
public interface RolesRepository extends JpaRepository<Roles, Long>{
	
	
	List<Roles> findAllByUsers_Id(Long id);
	
	void deleteRolesByUsers_Id(Long id);
	
	void deleteRolesByUsers_IdAndRole(Long id, Role role);

}
