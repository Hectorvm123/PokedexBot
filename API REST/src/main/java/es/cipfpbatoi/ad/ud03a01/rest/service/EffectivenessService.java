package es.cipfpbatoi.ad.ud03a01.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Effectiveness;
import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Types;
import es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa.IEffectivenessRepository;
import es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa.ITypesRepository;
import es.cipfpbatoi.ad.ud03a01.rest.dto.EffectivenessDTO;
import es.cipfpbatoi.ad.ud03a01.rest.dto.TypesDTO;
import es.cipfpbatoi.ad.ud03a01.rest.mapper.EffectivenessMapper;
import es.cipfpbatoi.ad.ud03a01.rest.mapper.TypesMapper;

@Service
public class EffectivenessService {
	@Autowired
	IEffectivenessRepository iEffectivenessRepository;
	
	@Autowired
	ITypesRepository iTypesRepository;
	public Long count() {
		return this.iEffectivenessRepository.count();
	}
	
	public List<EffectivenessDTO> findAll(){
		List<Effectiveness> effect = this.iEffectivenessRepository.findAll();
		return EffectivenessMapper.toDTO(effect);
	}

	
	
	public EffectivenessDTO findById(Integer id) {
		Optional<Effectiveness> effectOptional = this.iEffectivenessRepository.findById(id);
		if (effectOptional.isEmpty()) {
			return null;
		}
		return EffectivenessMapper.toDTO(effectOptional);	
	}
	
	
	public List<EffectivenessDTO> findByOffensiveType(String name) {
		Optional<Types> typeOptional = iTypesRepository.findByName(name);
		
		if (typeOptional.isEmpty()) {
			return null;
		}
		System.out.println();
		List<Effectiveness> effect = this.iEffectivenessRepository.findByOffensiveType(typeOptional.get());	
	
		return EffectivenessMapper.toDTO(effect);
	}
	
	public EffectivenessDTO create(EffectivenessDTO effectDTO) {
		Effectiveness effect = EffectivenessMapper.toBD(effectDTO);
		effect = this.iEffectivenessRepository.save(effect);
		return EffectivenessMapper.toDTO(effect);
	}
	public void update(EffectivenessDTO effectDTO) {
		Effectiveness effect = EffectivenessMapper.toBD(effectDTO);
		this.iEffectivenessRepository.save(effect);
	}
	public void delete(EffectivenessDTO effectDTO) {
		Effectiveness effect = EffectivenessMapper.toBD(effectDTO);
		this.iEffectivenessRepository.delete(effect);
	}
	
}
