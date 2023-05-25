package es.cipfpbatoi.ad.ud03a01.rest.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Pokemon;
import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Types;
import es.cipfpbatoi.ad.ud03a01.rest.dto.PokemonDTO;
import es.cipfpbatoi.ad.ud03a01.rest.dto.TypesDTO;

public class PokemonMapper {
	public static PokemonDTO toDTO(Pokemon pokemon, Boolean withType, Boolean withRegion) {
		PokemonDTO pokemonDTO = new PokemonDTO();
		pokemonDTO.setId(pokemon.getId());
		pokemonDTO.setName(pokemon.getName());
		pokemonDTO.setHp(pokemon.getHp());
		pokemonDTO.setAttack(pokemon.getAttack());
		pokemonDTO.setDefense(pokemon.getDefense());
		pokemonDTO.setSpecialAttack(pokemon.getSpecialAttack());
		pokemonDTO.setSpecialDefense(pokemon.getSpecialDefense());
		pokemonDTO.setSpeed(pokemon.getSpeed());
		pokemonDTO.setSpecies(pokemon.getSpecies());
		pokemonDTO.setDescription(pokemon.getDescription());
		pokemonDTO.setHeight(pokemon.getHeight());
		pokemonDTO.setWeight(pokemon.getWeight());
		pokemonDTO.setSprite(pokemon.getSprite());
		pokemonDTO.setThumbnail(pokemon.getThumbnail());
		pokemonDTO.setHires(pokemon.getHires());
		
		if (withRegion) {
			pokemonDTO.setRegion(RegionMapper.toDTO(pokemon.getRegion(), false));
		}
		
		if (withType) {
			List<TypesDTO> typeList = new ArrayList<>();
			for(Types types: pokemon.getTypes()) {
				TypesDTO temp = new TypesDTO();
				temp.setName(types.getName());
				typeList.add(temp);
			}
			pokemonDTO.setTypes(typeList);
		}
		
		return pokemonDTO;
	}
	
	
	public static PokemonDTO toDTO(Optional<Pokemon> pokeOptional, Boolean withType, Boolean withRegion) {
		if (pokeOptional.isEmpty()) {
			return null;
		}
		return PokemonMapper.toDTO(pokeOptional.get(), withType, withRegion);
	}
	
	
	public static List<PokemonDTO> toDTO(List<Pokemon> pokemons, Boolean withType, Boolean withRegion){
		List<PokemonDTO> pokemonList = new ArrayList<>();
		if(pokemons == null) {
			return pokemonList;
		}
		Pokemon temp = new Pokemon();
		temp.setId(43234);
		for (Pokemon pokemon: pokemons) {
			
			if (!temp.equals(pokemon)) {
				pokemonList.add(PokemonMapper.toDTO(pokemon, withType, withRegion));
			}
			temp = pokemon;
		}
		return pokemonList;
	}
	
	
	public static Pokemon toBD(PokemonDTO pokemonDTO, Boolean withType, Boolean withRegion) {
		Pokemon pokemon = new Pokemon();
		pokemon.setId(pokemonDTO.getId());
		pokemon.setName(pokemonDTO.getName());
		pokemon.setHp(pokemonDTO.getHp());
		pokemon.setAttack(pokemonDTO.getAttack());
		pokemon.setDefense(pokemonDTO.getDefense());
		pokemon.setSpecialAttack(pokemonDTO.getSpecialAttack());
		pokemon.setSpecialDefense(pokemonDTO.getSpecialDefense());
		pokemon.setSpeed(pokemonDTO.getSpeed());
		pokemon.setSpecies(pokemonDTO.getSpecies());
		pokemon.setDescription(pokemonDTO.getDescription());
		pokemon.setHeight(pokemonDTO.getHeight());
		pokemon.setWeight(pokemonDTO.getWeight());
		pokemon.setSprite(pokemonDTO.getSprite());
		pokemon.setThumbnail(pokemonDTO.getThumbnail());
		pokemon.setHires(pokemonDTO.getHires());
		
		if (withRegion) {
			pokemon.setRegion(RegionMapper.toBD(pokemonDTO.getRegion(), false));
		}
		
		if (withType) {
			List<Types> typeList = new ArrayList<>();
			for(TypesDTO typesDTO: pokemonDTO.getTypes()) {
				Types temp = new Types();
				temp.setName(typesDTO.getName());
				typeList.add(temp);
			}
			pokemon.setTypes(typeList);
		}
		
		return pokemon;
	}
	
	public static List<Pokemon> toBD(List<PokemonDTO> pokemonsDTO, Boolean withType, Boolean withRegion) {
		List<Pokemon> pokemons = new ArrayList<>();
		if (pokemonsDTO == null) {
			return pokemons;
		}
		for (PokemonDTO poke: pokemonsDTO) {
			pokemons.add(PokemonMapper.toBD(poke, withType, withRegion));
		}
		return pokemons;
	}
}
