package net.agm.hydra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.agm.hydra.model.Updates;
@Repository
public interface UpdatesRopository extends JpaRepository<Updates,Long> {

}
