package es.cipfpbatoi.ad.ud03a01.rest.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Types;
import es.cipfpbatoi.ad.ud03a01.rest.dto.TypesDTO;

public class TypesMapper {
	public static TypesDTO toDTO(Types types, Boolean withPoke) {
		TypesDTO typesDTO = new TypesDTO();
		typesDTO.setId(types.getId());
		typesDTO.setName(types.getName());
		
		if (withPoke) {
			typesDTO.setPokemons(PokemonMapper.toDTO(types.getPokemons(), true, true));
		}
		
		return typesDTO;
	}
	
	
	public static TypesDTO toDTO(Optional<Types> typesOptional, Boolean withPoke) {
		if (typesOptional.isEmpty()) {
			return null;
		}
		return TypesMapper.toDTO(typesOptional.get(), withPoke);
	}
	
	
	public static List<TypesDTO> toDTO(List<Types> types, Boolean withPoke){
		List<TypesDTO> typesDTO = new ArrayList<>();
		if(types == null) {
			return typesDTO;
		}
		
		for (Types type: types) {
			typesDTO.add(TypesMapper.toDTO(type, withPoke));
		}
		return typesDTO;
	}
	
	
	public static Types toBD(TypesDTO typesDTO, Boolean withPoke) {
		Types types = new Types();
		types.setId(typesDTO.getId());
		types.setName(typesDTO.getName());
	
		return types;
	}
	public static List<Types> toBD(List<TypesDTO> typesDTO, Boolean withPoke) {
		List<Types> types = new ArrayList<>();
		if (typesDTO == null) {
			return types;
		}
		for (TypesDTO typeDTO: typesDTO) {
			types.add(TypesMapper.toBD(typeDTO, withPoke));
		}
		return types;
	}
}
