package es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Types;

public interface ITypesRepository extends JpaRepository<Types, Integer>{
	
	@Query("SELECT t FROM Types t WHERE t.name = :name")
	Optional<Types> findByName(String name);
	
}
