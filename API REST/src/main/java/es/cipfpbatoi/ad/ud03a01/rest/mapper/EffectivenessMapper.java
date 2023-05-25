package es.cipfpbatoi.ad.ud03a01.rest.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Effectiveness;
import es.cipfpbatoi.ad.ud03a01.persistencia.modelo.Types;
import es.cipfpbatoi.ad.ud03a01.rest.dto.EffectivenessDTO;
import es.cipfpbatoi.ad.ud03a01.rest.dto.TypesDTO;

public class EffectivenessMapper {
	public static EffectivenessDTO toDTO(Effectiveness effect) {
		EffectivenessDTO effectDTO = new EffectivenessDTO();
		effectDTO.setId(effect.getId());
		effectDTO.setOffensiveType(TypesMapper.toDTO(effect.getOffensiveType(), false));
		effectDTO.setDefensiveType(TypesMapper.toDTO(effect.getDefensiveType(), false));
		effectDTO.setEffect(effect.getEffect());

		return effectDTO;
	}
	
	
	public static EffectivenessDTO toDTO(Optional<Effectiveness> effectOptional) {
		if (effectOptional.isEmpty()) {
			return null;
		}
		return EffectivenessMapper.toDTO(effectOptional.get());
	}
	
	
	public static List<EffectivenessDTO> toDTO(List<Effectiveness> effect){
		List<EffectivenessDTO> effectDTO = new ArrayList<>();
		if(effect == null) {
			return effectDTO;
		}
		
		for (Effectiveness effectiveness: effect) {
			effectDTO.add(EffectivenessMapper.toDTO(effectiveness));
		}
		return effectDTO;
	}
	
	
	public static Effectiveness toBD(EffectivenessDTO effectDTO) {
		Effectiveness effect = new Effectiveness();
		effect.setId(effectDTO.getId());
		effect.setOffensiveType(TypesMapper.toBD(effectDTO.getOffensiveType(), false));
		effect.setDefensiveType(TypesMapper.toBD(effectDTO.getDefensiveType(), false));
		effect.setEffect(effectDTO.getEffect());

	
		return effect;
	}
	public static List<Effectiveness> toBD(List<EffectivenessDTO> effectDTO) {
		List<Effectiveness> effect = new ArrayList<>();
		if (effectDTO == null) {
			return effect;
		}
		for (EffectivenessDTO effectivenessDTO: effectDTO) {
			effect.add(EffectivenessMapper.toBD(effectivenessDTO));
		}
		return effect;
	}
}
