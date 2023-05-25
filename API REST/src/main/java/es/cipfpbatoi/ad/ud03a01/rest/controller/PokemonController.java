package es.cipfpbatoi.ad.ud03a01.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.cipfpbatoi.ad.ud03a01.rest.dto.PokemonDTO;
import es.cipfpbatoi.ad.ud03a01.rest.dto.TypesDTO;
import es.cipfpbatoi.ad.ud03a01.rest.service.PokemonService;
import es.cipfpbatoi.ad.ud03a01.rest.service.TypesService;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {
	
	@Autowired
	PokemonService pokemonService;
	
	@GetMapping("/count")
	public Long count() {
		return this.pokemonService.count();
	}
	
	@GetMapping("")
	public List<PokemonDTO> findAll(@RequestParam(required = false, defaultValue = "false") Boolean withType) {
		return this.pokemonService.findAll(withType, true);
	}
	
	@GetMapping("/{id}")
	public PokemonDTO findById(@PathVariable Integer id,
								@RequestParam(required = false, defaultValue = "true") Boolean withType) {
		PokemonDTO pokemonDTO = this.pokemonService.findById(id, withType, true);
		if (pokemonDTO == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
		}
		return pokemonDTO;
	}
	
	@GetMapping("/name/{name}")
	public PokemonDTO findByName(@PathVariable String name,
								@RequestParam(required = false, defaultValue = "true") Boolean withType) {
		PokemonDTO pokemonDTO = this.pokemonService.findByName(name, withType, true);
		if (pokemonDTO == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
		}
		return pokemonDTO;
	}
	
	@GetMapping("/region/{name}")
	public List<PokemonDTO> findByRegion(@PathVariable String name,
								@RequestParam(required = false, defaultValue = "true") Boolean withType) {
		return this.pokemonService.findByRegion(name, withType, true);
	}
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PokemonDTO create(@RequestBody PokemonDTO pokemonDTO) {
		return this.pokemonService.create(pokemonDTO, true, true);
	}
	
	
	@PostMapping("/all")
	@ResponseStatus(code = HttpStatus.CREATED)
	public List<PokemonDTO> createAll(@RequestBody List<PokemonDTO> pokemonDTO) {
		return this.pokemonService.createAll(pokemonDTO, true, true);
	}
	
	
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity updated")
	public void update(@PathVariable int id, @RequestBody PokemonDTO pokemonDTO) {
		if (id != pokemonDTO.getId()) {
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Error in query");
		}
		this.pokemonService.update(pokemonDTO, true, true);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity deleted")
	public void delete(@PathVariable int id, @RequestBody PokemonDTO pokemonDTO) {
		if (id != pokemonDTO.getId()) {
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Error in query");
		}
		this.pokemonService.delete(pokemonDTO, false, false);
		
	}
}
