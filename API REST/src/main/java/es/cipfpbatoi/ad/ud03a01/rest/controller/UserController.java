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
import es.cipfpbatoi.ad.ud03a01.rest.dto.UserDTO;
import es.cipfpbatoi.ad.ud03a01.rest.service.TypesService;
import es.cipfpbatoi.ad.ud03a01.rest.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/count")
	public Long count() {
		return this.userService.count();
	}
	
	@GetMapping("")
	public List<UserDTO> findAll(@RequestParam(required = false, defaultValue = "false") Boolean withPokeCaught) {
		return this.userService.findAll(withPokeCaught);
	}
	
	@GetMapping("/{id}")
	public UserDTO findById(@PathVariable Integer id, 
							@RequestParam(required = false, defaultValue = "false") Boolean withPokeCaught) {
		UserDTO userDTO = this.userService.findById(id, withPokeCaught);
		if (userDTO == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
		}
		return userDTO;
	}
	
	@GetMapping("/name/{name}")
	public UserDTO findByName(@PathVariable String name, 
							@RequestParam(required = false, defaultValue = "false") Boolean withPokeCaught) {
		
		UserDTO userDTO = this.userService.findByName(name);
		if (userDTO == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
		}
		return userDTO;
	}
	
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserDTO create(@RequestBody UserDTO userDTO) {
		return this.userService.create(userDTO, true);
	}
	
	@PutMapping("")
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity updated")
	public void update(@RequestBody UserDTO userDTO) {
		
		this.userService.update(userDTO, true);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity deleted")
	public void delete(@PathVariable int id, @RequestBody UserDTO userDTO) {
		if (id != userDTO.getId()) {
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Error in query");
		}
		this.userService.delete(userDTO, false);
		
	}
}
