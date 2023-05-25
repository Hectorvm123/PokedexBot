package es.cipfpbatoi.ad.ud03a01.rest.dto;

import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class PokemonDTO {
	private Integer id;
	private String name;
	private Integer hp;
	private Integer attack;
	private Integer defense;
	private Integer specialAttack;
	private Integer specialDefense;
	private Integer speed;
	private String species;
	private String description;
	private String height;
	private String weight;
	private String sprite;
	private String thumbnail;
	private String hires;
	private RegionDTO region;
	
	
	
	public String getSprite() {
		return sprite;
	}
	public void setSprite(String sprite) {
		this.sprite = sprite;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getHires() {
		return hires;
	}
	public void setHires(String hires) {
		this.hires = hires;
	}
	public RegionDTO getRegion() {
		return region;
	}
	public void setRegion(RegionDTO region) {
		this.region = region;
	}
	private List<TypesDTO> types;
	
	
	
	public List<TypesDTO> getTypes() {
		return types;
	}
	public void setTypes(List<TypesDTO> types) {
		this.types = types;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getHp() {
		return hp;
	}
	public void setHp(Integer hp) {
		this.hp = hp;
	}
	public Integer getAttack() {
		return attack;
	}
	public void setAttack(Integer attack) {
		this.attack = attack;
	}
	public Integer getDefense() {
		return defense;
	}
	public void setDefense(Integer defense) {
		this.defense = defense;
	}
	public Integer getSpecialAttack() {
		return specialAttack;
	}
	public void setSpecialAttack(Integer specialAttack) {
		this.specialAttack = specialAttack;
	}
	public Integer getSpecialDefense() {
		return specialDefense;
	}
	public void setSpecialDefense(Integer specialDefense) {
		this.specialDefense = specialDefense;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PokemonDTO other = (PokemonDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
}