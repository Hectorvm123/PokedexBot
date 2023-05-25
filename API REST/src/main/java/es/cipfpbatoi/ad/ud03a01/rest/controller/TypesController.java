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
import es.cipfpbatoi.ad.ud03a01.rest.service.TypesService;

@RestController
@RequestMapping("/types")
public class TypesController {
	
	@Autowired
	TypesService typesService;
	
	@GetMapping("/count")
	public Long count() {
		return this.typesService.count();
	}
	
	@GetMapping("")
	public List<TypesDTO> findAll(@RequestParam(required = false, defaultValue = "false") Boolean withPoke) {
		return this.typesService.findAll(withPoke);
	}
	
	@GetMapping("/{id}")
	public TypesDTO findById(@PathVariable Integer id,
								@RequestParam(required = false, defaultValue = "false") Boolean withPoke) {
		TypesDTO typesDTO = this.typesService.findById(id, withPoke);
		if (typesDTO == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
		}
		return typesDTO;
	}
	
	@GetMapping("/name/{name}")
	public TypesDTO findByName(@PathVariable String name,
								@RequestParam(required = false, defaultValue = "true") Boolean withPoke){
		TypesDTO typeDTO = this.typesService.findByName(name, withPoke);
		if (typeDTO == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
		}
		return typeDTO;
	}
	
	
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TypesDTO create(@RequestBody TypesDTO typesDTO) {
		return this.typesService.create(typesDTO, true);
	}
	
	
	@PostMapping("/all")
	@ResponseStatus(code = HttpStatus.CREATED)
	public List<TypesDTO> createAll(@RequestBody List<TypesDTO> typesDTO) {
		return this.typesService.createAll(typesDTO, true);
	}
	
	
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity updated")
	public void update(@PathVariable int id, @RequestBody TypesDTO typesDTO) {
		if (id != typesDTO.getId()) {
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Error in query");
		}
		this.typesService.update(typesDTO, true);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity deleted")
	public void delete(@PathVariable int id, @RequestBody TypesDTO typesDTO) {
		if (id != typesDTO.getId()) {
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Error in query");
		}
		this.typesService.delete(typesDTO, false);
		
	}
}
