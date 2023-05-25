package es.cipfpbatoi.ad.ud03a01.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Pokemon;
import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Types;
import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.User;
import es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa.IPokemonRepository;
import es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa.ITypesRepository;
import es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa.IUserRepository;
import es.cipfpbatoi.ad.ud03a01.rest.dto.PokemonDTO;
import es.cipfpbatoi.ad.ud03a01.rest.dto.TypesDTO;
import es.cipfpbatoi.ad.ud03a01.rest.dto.UserDTO;
import es.cipfpbatoi.ad.ud03a01.rest.mapper.PokemonMapper;
import es.cipfpbatoi.ad.ud03a01.rest.mapper.TypesMapper;
import es.cipfpbatoi.ad.ud03a01.rest.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	IUserRepository iUserRepository;
	
	@Autowired
	IPokemonRepository ipoPokemonRepository;
	
	public Long count() {
		return this.iUserRepository.count();
	}
	
	public List<UserDTO> findAll(Boolean withPokeCaught){
		List<User> users = this.iUserRepository.findAll();
		return UserMapper.toDTO(users, withPokeCaught);
	}

	
	
	public UserDTO findById(Integer id,Boolean withPokeCaught) {
		
		Optional<User> userOptional = this.iUserRepository.findById(id);
		if (userOptional.isEmpty()) {
			return null;
		}
		return UserMapper.toDTO(userOptional, withPokeCaught);	
	}
	
	public UserDTO findByName(String name) {
		
		
		Optional<User> userOptional = this.iUserRepository.findByName(name);
		if (userOptional.isEmpty()) {
			return null;
		}
		return UserMapper.toDTO(userOptional, true);	
	}
	
	
	
	public UserDTO create(UserDTO userDTO,Boolean withPokeCaught) {
		User user = UserMapper.toBD(userDTO,withPokeCaught);
		user = this.iUserRepository.save(user);
		return UserMapper.toDTO(user, withPokeCaught);

	}
	public void update(UserDTO userDTO,Boolean withPokeCaught) {
		
		User user = UserMapper.toBD(userDTO, true);
		
		Optional<User> userOptional = iUserRepository.findByName(user.getName());
		if (!userOptional.isEmpty()) {
			User userTemp = userOptional.get();
		
		
			if (withPokeCaught) {
				List<Pokemon> temp =  user.getPokemons();
				Optional<Pokemon> pokeCaught = ipoPokemonRepository.findById(temp.get(0).getId());
				if (!pokeCaught.isEmpty()) {
					pokeCaught.get().getUsers().add(user);
					user.getPokemons().clear();
					
					
					user.getPokemons().add(pokeCaught.get());
					user.getPokemons().addAll(userTemp.getPokemons());
				}
			}
		}
		
		
		this.iUserRepository.save(user);
	}
	public void delete(UserDTO userDTO,Boolean withPokeCaught) {
		User user = UserMapper.toBD(userDTO, withPokeCaught);
		this.iUserRepository.delete(user);
	}

	
}
