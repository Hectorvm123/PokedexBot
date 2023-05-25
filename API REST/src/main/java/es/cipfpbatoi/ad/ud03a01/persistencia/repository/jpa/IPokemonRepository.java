package es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Pokemon;


public interface IPokemonRepository extends JpaRepository<Pokemon, Integer>{
	@Query("SELECT p FROM Pokemon p JOIN FETCH p.types t JOIN FETCH p.region r WHERE p.name LIKE :name")
	Optional<Pokemon> findByName(String name);
	
	@Query("SELECT p FROM Pokemon p JOIN FETCH p.types t JOIN FETCH p.region r WHERE r.name LIKE :name")
	List<Pokemon> findByRegion(String name);
	
}
