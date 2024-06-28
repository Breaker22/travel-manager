package ar.com.travel.manager.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.travel.manager.model.entity.Path;

@Repository
public interface PathRepository extends JpaRepository<Path, Long> {

}
