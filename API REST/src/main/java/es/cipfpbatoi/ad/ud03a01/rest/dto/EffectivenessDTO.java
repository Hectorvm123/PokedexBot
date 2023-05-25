package es.cipfpbatoi.ad.ud03a01.rest.dto;

public class EffectivenessDTO {
	private Integer id;
	private TypesDTO offensiveType;
	private TypesDTO defensiveType;
	private Float effect;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TypesDTO getOffensiveType() {
		return offensiveType;
	}

	public void setOffensiveType(TypesDTO offensiveType) {
		this.offensiveType = offensiveType;
	}

	public TypesDTO getDefensiveType() {
		return defensiveType;
	}

	public void setDefensiveType(TypesDTO defensiveType) {
		this.defensiveType = defensiveType;
	}

	public Float getEffect() {
		return effect;
	}

	public void setEffect(Float effect) {
		this.effect = effect;
	}
	
	
	
	
}

