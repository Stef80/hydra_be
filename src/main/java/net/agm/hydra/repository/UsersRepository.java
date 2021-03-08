package net.agm.hydra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.agm.hydra.datamodel.Role;
import net.agm.hydra.model.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	
	Users findUsersByEmail(String email);
	
	List<Users> findAllByRoleses_role(Role role);

}
