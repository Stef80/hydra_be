package net.agm.hydra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.agm.hydra.datamodel.Role;
import net.agm.hydra.model.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	
	Users findUsersByEmail(String email);
	
	List<Users> findAllByRoleses_role(Role role);

	 @Query("SELECT p from Users p WHERE p.id = :id")
	    Optional<Users> findById(Long id);
}
