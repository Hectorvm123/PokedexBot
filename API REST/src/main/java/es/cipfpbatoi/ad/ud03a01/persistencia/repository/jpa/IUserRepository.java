package es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.User;

public interface IUserRepository extends JpaRepository<User, Integer>{
	@Query("SELECT u FROM User u where u.name LIKE :name")
	Optional<User> findByName(String name);
	
}
