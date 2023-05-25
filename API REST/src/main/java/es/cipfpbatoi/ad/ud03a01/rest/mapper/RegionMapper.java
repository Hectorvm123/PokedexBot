package es.cipfpbatoi.ad.ud03a01.rest.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Region;
import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Types;
import es.cipfpbatoi.ad.ud03a01.rest.dto.RegionDTO;
import es.cipfpbatoi.ad.ud03a01.rest.dto.TypesDTO;

public class RegionMapper {
	public static RegionDTO toDTO(Region region, Boolean withPoke) {
		RegionDTO regionDTO = new RegionDTO();
		regionDTO.setId(region.getId());
		regionDTO.setName(region.getName());
		
		return regionDTO;
	}
	
	
	public static RegionDTO toDTO(Optional<Region> regionOptional, Boolean withPoke) {
		if (regionOptional.isEmpty()) {
			return null;
		}
		return RegionMapper.toDTO(regionOptional.get(), withPoke);
	}
	
	
	public static List<RegionDTO> toDTO(List<Region> regions, Boolean withPoke){
		List<RegionDTO> regionsDTO = new ArrayList<>();
		if(regions == null) {
			return regionsDTO;
		}
		
		for (Region region: regions) {
			regionsDTO.add(RegionMapper.toDTO(region, withPoke));
		}
		return regionsDTO;
	}
	
	
	public static Region toBD(RegionDTO regionDTO, Boolean withPoke) {
		Region region = new Region();
		region.setId(regionDTO.getId());
		region.setName(regionDTO.getName());
	
		return region;
	}
	public static List<Region> toBD(List<RegionDTO> regionsDTO, Boolean withPoke) {
		List<Region> regions = new ArrayList<>();
		if (regionsDTO == null) {
			return regions;
		}
		for (RegionDTO regionDTO: regionsDTO) {
			regions.add(RegionMapper.toBD(regionDTO, withPoke));
		}
		return regions;
	}
}
