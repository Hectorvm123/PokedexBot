package es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Effectiveness;
import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Types;

public interface IEffectivenessRepository extends JpaRepository<Effectiveness, Integer>{
	
	@Query("SELECT e FROM Effectiveness e WHERE e.offensiveType = :typeId")
	List<Effectiveness> findByOffensiveType(Types typeId);
	
	@Query("SELECT e FROM Effectiveness e WHERE e.defensiveType = :typeId")
	List<Effectiveness> findByDefensiveType(Integer typeId);
}
