package es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Region;
import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Types;

public interface IRegionRepository extends JpaRepository<Region, Integer>{
	
	@Query("SELECT r FROM Region r WHERE r.name = :name")
	Region findByName(String name);
	
}
