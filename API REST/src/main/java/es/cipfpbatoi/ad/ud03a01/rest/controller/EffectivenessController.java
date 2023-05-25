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

import es.cipfpbatoi.ad.ud03a01.rest.dto.EffectivenessDTO;
import es.cipfpbatoi.ad.ud03a01.rest.dto.PokemonDTO;
import es.cipfpbatoi.ad.ud03a01.rest.dto.TypesDTO;
import es.cipfpbatoi.ad.ud03a01.rest.service.EffectivenessService;
import es.cipfpbatoi.ad.ud03a01.rest.service.TypesService;

@RestController
@RequestMapping("/effects")
public class EffectivenessController {
	
	@Autowired
	EffectivenessService effectivenessService;
	
	@GetMapping("/count")
	public Long count() {
		return this.effectivenessService.count();
	}
	
	@GetMapping("")
	public List<EffectivenessDTO> findAll() {
		return this.effectivenessService.findAll();
	}
	
	@GetMapping("/{id}")
	public EffectivenessDTO findById(@PathVariable Integer id) {
		EffectivenessDTO effectDTO = this.effectivenessService.findById(id);
		if (effectDTO == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
		}
		return effectDTO;
	}	
	
	@GetMapping("/attack/{name}")
	public List<EffectivenessDTO> findByAttack(@PathVariable String name) {
		List<EffectivenessDTO> effectDTO = this.effectivenessService.findByOffensiveType(name);
		if (effectDTO == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
		}
		return effectDTO;
	}
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EffectivenessDTO create(@RequestBody EffectivenessDTO effectDTO) {
		return this.effectivenessService.create(effectDTO);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity updated")
	public void update(@PathVariable int id, @RequestBody EffectivenessDTO effectDTO) {
		if (id != effectDTO.getId()) {
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Error in query");
		}
		this.effectivenessService.update(effectDTO);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity deleted")
	public void delete(@PathVariable int id, @RequestBody EffectivenessDTO effectDTO) {
		if (id != effectDTO.getId()) {
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Error in query");
		}
		this.effectivenessService.delete(effectDTO);
		
	}
}
