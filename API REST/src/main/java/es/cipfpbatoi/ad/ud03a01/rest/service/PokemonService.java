package es.cipfpbatoi.ad.ud03a01.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Pokemon;
import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Region;
import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Types;
import es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa.IPokemonRepository;
import es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa.IRegionRepository;
import es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa.ITypesRepository;
import es.cipfpbatoi.ad.ud03a01.rest.dto.PokemonDTO;
import es.cipfpbatoi.ad.ud03a01.rest.dto.TypesDTO;
import es.cipfpbatoi.ad.ud03a01.rest.mapper.PokemonMapper;
import es.cipfpbatoi.ad.ud03a01.rest.mapper.TypesMapper;

@Service
public class PokemonService {
	@Autowired
	IPokemonRepository iPokemonRepository;
	
	@Autowired
	ITypesRepository iTypesRepository;
	
	@Autowired
	IRegionRepository iRegionRepository;
	
	public Long count() {
		return this.iPokemonRepository.count();
	}
	
	public List<PokemonDTO> findAll(Boolean withType, Boolean withRegion){
		List<Pokemon> pokemons = this.iPokemonRepository.findAll();
		return PokemonMapper.toDTO(pokemons, withType, withRegion);
	}
	
	public List<PokemonDTO> findByRegion(String name, Boolean withType, Boolean withRegion){
		List<Pokemon> pokemons = this.iPokemonRepository.findByRegion(name);
		return PokemonMapper.toDTO(pokemons, withType, withRegion);
	}

	
	
	public PokemonDTO findById(Integer id, Boolean withType, Boolean withRegion) {
		
		Optional<Pokemon> pokeOptional = this.iPokemonRepository.findById(id);
		if (pokeOptional.isEmpty()) {
			return null;
		}
		return PokemonMapper.toDTO(pokeOptional, withType, withRegion);	
	}

	public PokemonDTO findByName(String name, Boolean withType, Boolean withRegion) {
		
		Optional<Pokemon> pokeOptional = this.iPokemonRepository.findByName(name);
		if (pokeOptional.isEmpty()) {
			return null;
		}
		return PokemonMapper.toDTO(pokeOptional, withType, withRegion);	
	}
	
	
	
	public PokemonDTO create(PokemonDTO pokemonDTO, Boolean withType, Boolean withRegion) {
		Pokemon pokemon = PokemonMapper.toBD(pokemonDTO, withType, withRegion);
		List<Types> temp = iTypesRepository.findAll();
		List<Types> typesOfPokemon = new ArrayList<>();
		typesOfPokemon.addAll(pokemon.getTypes());
		pokemon.getTypes().clear();
		
		List<Region> regions = iRegionRepository.findAll();
		for (Region region: regions) {
			if (region.getName().equals(pokemon.getRegion().getName())) {
				pokemon.getRegion().setId(region.getId());
			}
		}
		
		
		
		for(Types type: typesOfPokemon) {
			for (Types typeTemp: temp){
				if (typeTemp.equals(type)) {
					Types moreTemp = typeTemp;
					
					//pokemon.getTypes().remove(type);
					
					moreTemp.getPokemons().add(pokemon);
					pokemon.getTypes().add(moreTemp);
					

				}
			}
		}
	
		pokemon = this.iPokemonRepository.save(pokemon);
		return PokemonMapper.toDTO(pokemon, withType, withRegion);
	}
	public void update(PokemonDTO pokemonDTO, Boolean withType, Boolean withRegion) {
		Pokemon pokemon = PokemonMapper.toBD(pokemonDTO, withType, withRegion);
		this.iPokemonRepository.save(pokemon);
	}
	public void delete(PokemonDTO pokemonDTO, Boolean withType, Boolean withRegion) {
		Pokemon pokemon = PokemonMapper.toBD(pokemonDTO, withType, withRegion);
		this.iPokemonRepository.save(pokemon);
	}


	public List<PokemonDTO> createAll(List<PokemonDTO> pokemons, Boolean withTypes, Boolean withRegion) {
		List<PokemonDTO> pokeList = new ArrayList<>();
		for (PokemonDTO pokemon: pokemons) {
			pokeList.add(this.create(pokemon, withTypes, withRegion));
		}
		
		return pokeList;
	}
	
}
