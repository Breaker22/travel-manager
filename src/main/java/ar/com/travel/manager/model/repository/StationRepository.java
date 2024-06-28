package ar.com.travel.manager.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.travel.manager.model.entity.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

}
