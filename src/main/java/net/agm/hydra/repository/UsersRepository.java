package net.agm.hydra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.agm.hydra.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
