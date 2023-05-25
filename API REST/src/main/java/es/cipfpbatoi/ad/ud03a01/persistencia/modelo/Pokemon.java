package es.cipfpbatoi.ad.ud03a01.persistencia.modelo;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name="pokemon")
@JsonInclude(Include.NON_NULL)
public class Pokemon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "HP")
	private Integer hp;
	@Column(name = "ATTACK")
	private Integer attack;
	@Column(name = "DEFENSE")
	private Integer defense;
	@Column(name = "SPECIAL_ATTACK")
	private Integer specialAttack;
	@Column(name = "SPECIAL_DEFENSE")
	private Integer specialDefense;
	@Column(name = "SPEED")
	private Integer speed;
	@Column(name = "SPECIES")
	private String species;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "HEIGHT")
	private String height;
	@Column(name = "WEIGHT")
	private String weight;
	@Column(name = "SPRITE")
	private String sprite;
	@Column(name = "THUMBNAIL")
	private String thumbnail;
	@Column(name = "HIRES")
	private String hires;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REGION", referencedColumnName = "id")
	private Region region;
	
	
	
	
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
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "poke_types", 
				joinColumns = {
						@JoinColumn(name = "POKE_ID", referencedColumnName = "id")
				},
				inverseJoinColumns = {
						@JoinColumn(name = "TYPE_ID", referencedColumnName = "id")
				})
	private List<Types> types;
	
	@ManyToMany(mappedBy = "pokemons", fetch = FetchType.LAZY)
	private List<User> users;
	
	
	
	public List<Types> getTypes() {
		return types;
	}
	public void setTypes(List<Types> types) {
		this.types = types;
	}
	
	public void addType(Types t) {
        this.types.add(t);
        t.getPokemons().add(this);
    }
 
    public void removeType(Types t) {
        this.types.remove(t);
        t.getPokemons().remove(this);
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
	
	
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	
	
	
	
	
}