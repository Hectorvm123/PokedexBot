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

import es.cipfpbatoi.ad.ud03a01.rest.dto.RegionDTO;
import es.cipfpbatoi.ad.ud03a01.rest.dto.TypesDTO;
import es.cipfpbatoi.ad.ud03a01.rest.service.RegionService;
import es.cipfpbatoi.ad.ud03a01.rest.service.TypesService;

@RestController
@RequestMapping("/region")
public class RegionController {
	
	@Autowired
	RegionService regionService;
	
	@GetMapping("/count")
	public Long count() {
		return this.regionService.count();
	}
	
	@GetMapping("")
	public List<RegionDTO> findAll(@RequestParam(required = false, defaultValue = "false") Boolean withPoke) {
		return this.regionService.findAll(withPoke);
	}
	
	@GetMapping("/{id}")
	public RegionDTO findById(@PathVariable Integer id,
								@RequestParam(required = false, defaultValue = "false") Boolean withPoke) {
		RegionDTO regionDTO = this.regionService.findById(id, withPoke);
		if (regionDTO == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
		}
		return regionDTO;
	}
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RegionDTO create(@RequestBody RegionDTO regionDTO) {
		return this.regionService.create(regionDTO, true);
	}
	
	/*
	@PostMapping("/all")
	@ResponseStatus(code = HttpStatus.CREATED)
	public List<TypesDTO> createAll(@RequestBody List<TypesDTO> typesDTO) {
		return this.typesService.createAll(typesDTO, true);
	}
	*/
	
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity updated")
	public void update(@PathVariable int id, @RequestBody RegionDTO regionDTO) {
		if (id != regionDTO.getId()) {
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Error in query");
		}
		this.regionService.update(regionDTO, true);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity deleted")
	public void delete(@PathVariable int id, @RequestBody RegionDTO regionDTO) {
		if (id != regionDTO.getId()) {
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Error in query");
		}
		this.regionService.delete(regionDTO, false);
		
	}
}
