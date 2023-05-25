package es.cipfpbatoi.ad.ud03a01.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Region;
import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Types;
import es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa.IRegionRepository;
import es.cipfpbatoi.ad.ud03a01.persistencia.repository.jpa.ITypesRepository;
import es.cipfpbatoi.ad.ud03a01.rest.dto.RegionDTO;
import es.cipfpbatoi.ad.ud03a01.rest.dto.TypesDTO;
import es.cipfpbatoi.ad.ud03a01.rest.mapper.RegionMapper;
import es.cipfpbatoi.ad.ud03a01.rest.mapper.TypesMapper;

@Service
public class RegionService {
	@Autowired
	IRegionRepository iRegionRepository;
	
	public Long count() {
		return this.iRegionRepository.count();
	}
	
	public List<RegionDTO> findAll(Boolean withChara){
		List<Region> regions = this.iRegionRepository.findAll();
		return RegionMapper.toDTO(regions, withChara);
	}

	
	
	public RegionDTO findById(Integer id, Boolean withPoke) {
		
		Optional<Region> regionOptional = this.iRegionRepository.findById(id);
		if (regionOptional.isEmpty()) {
			return null;
		}
		return RegionMapper.toDTO(regionOptional, withPoke);	
	}
	
	
	
	public RegionDTO create(RegionDTO regionDTO, Boolean withPoke) {
		Region region = RegionMapper.toBD(regionDTO, withPoke);
		region = this.iRegionRepository.save(region);
		return RegionMapper.toDTO(region, withPoke);
	}
	public void update(RegionDTO regionDTO, Boolean withPoke) {
		Region region = RegionMapper.toBD(regionDTO, withPoke);
		this.iRegionRepository.save(region);
	}
	public void delete(RegionDTO regionDTO, Boolean withPoke) {
		Region region = RegionMapper.toBD(regionDTO, withPoke);
		this.iRegionRepository.delete(region);
	}
/*
	public List<TypesDTO> createAll(List<TypesDTO> types, Boolean withPoke) {
		List<TypesDTO> typeList = new ArrayList<>();
		for (TypesDTO type: types) {
			Types typeTemp = TypesMapper.toBD(type, withPoke);
			this.itypesRepository.save(typeTemp);
			typeList.add(TypesMapper.toDTO(typeTemp, withPoke));
		}
		
		return typeList;
	}
	*/
}
