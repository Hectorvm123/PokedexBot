package es.cipfpbatoi.ad.ud03a01.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Types;
import es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa.ITypesRepository;
import es.cipfpbatoi.ad.ud03a01.rest.dto.TypesDTO;
import es.cipfpbatoi.ad.ud03a01.rest.mapper.TypesMapper;

@Service
public class TypesService {
	@Autowired
	ITypesRepository itypesRepository;
	
	public Long count() {
		return this.itypesRepository.count();
	}
	
	public List<TypesDTO> findAll(Boolean withChara){
		List<Types> types = this.itypesRepository.findAll();
		return TypesMapper.toDTO(types, withChara);
	}

	
	
	public TypesDTO findById(Integer id, Boolean withPoke) {
		Optional<Types> typeOptional = this.itypesRepository.findById(id);
		if (typeOptional.isEmpty()) {
			return null;
		}
		return TypesMapper.toDTO(typeOptional, withPoke);	
	}
	
	public TypesDTO findByName(String name, Boolean withPoke) {
		Optional<Types> typeOptional = this.itypesRepository.findByName(name);
		if (typeOptional.isEmpty()) {
			return null;
		}
		return TypesMapper.toDTO(typeOptional, withPoke);	
	}
	
	
	
	public TypesDTO create(TypesDTO typesDTO, Boolean withPoke) {
		Types type = TypesMapper.toBD(typesDTO, withPoke);
		type = this.itypesRepository.save(type);
		return TypesMapper.toDTO(type, withPoke);
	}
	public void update(TypesDTO typesDTO, Boolean withPoke) {
		Types type = TypesMapper.toBD(typesDTO, withPoke);
		this.itypesRepository.save(type);
	}
	public void delete(TypesDTO pokemonDTO, Boolean withPoke) {
		Types type = TypesMapper.toBD(pokemonDTO, withPoke);
		this.itypesRepository.delete(type);
	}

	public List<TypesDTO> createAll(List<TypesDTO> types, Boolean withPoke) {
		List<TypesDTO> typeList = new ArrayList<>();
		for (TypesDTO type: types) {
			Types typeTemp = TypesMapper.toBD(type, withPoke);
			this.itypesRepository.save(typeTemp);
			typeList.add(TypesMapper.toDTO(typeTemp, withPoke));
		}
		
		return typeList;
	}
	
}
