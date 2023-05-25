package es.cipfpbatoi.ad.ud03a01.rest.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Pokemon;
import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Types;
import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.User;
import es.cipfpbatoi.ad.ud03a01.rest.dto.PokemonDTO;
import es.cipfpbatoi.ad.ud03a01.rest.dto.TypesDTO;
import es.cipfpbatoi.ad.ud03a01.rest.dto.UserDTO;

public class UserMapper {
	public static UserDTO toDTO(User user, Boolean withPokeCaught) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		
		if (withPokeCaught) {
			userDTO.setPokemonDTO(PokemonMapper.toDTO(user.getPokemons(), true, true));
		}
		
		return userDTO;
	}
	
	
	public static UserDTO toDTO(Optional<User> userOptional, Boolean withPokeCaught) {
		if (userOptional.isEmpty()) {
			return null;
		}
		return UserMapper.toDTO(userOptional.get(), withPokeCaught);
	}
	
	
	public static List<UserDTO> toDTO(List<User> users, Boolean withPokeCaught){
		List<UserDTO> userList = new ArrayList<>();
		if(users == null) {
			return userList;
		}
		
		for (User user: users) {
			userList.add(UserMapper.toDTO(user, withPokeCaught));
		}
		return userList;
	}
	
	
	public static User toBD(UserDTO userDTO, Boolean withPokeCaught) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		
		if (withPokeCaught) {
			user.setPokemons(PokemonMapper.toBD(userDTO.getPokemonDTO(), false, false));
		}
		
		
		return user;
	}
}
