package es.cipfpbatoi.ad.ud03a01.rest.dto;

import java.util.List;
import java.util.Objects;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Pokemon;
import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Types;

@JsonInclude(Include.NON_NULL)
public class TypesDTO {
	private Integer id;
	private String name;
	private List<PokemonDTO> pokemons;
	private List<TypesDTO> types;




	public List<PokemonDTO> getPokemons() {
		return pokemons;
	}

	public void setPokemons(List<PokemonDTO> pokemons) {
		this.pokemons = pokemons;
	}

	public List<TypesDTO> getTypes() {
		return types;
	}

	public void setTypes(List<TypesDTO> types) {
		this.types = types;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TypesDTO other = (TypesDTO) obj;
		return Objects.equals(id, other.id);
	}
	
}