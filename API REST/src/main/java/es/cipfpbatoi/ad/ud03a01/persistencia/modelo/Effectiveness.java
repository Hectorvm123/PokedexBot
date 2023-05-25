package es.cipfpbatoi.ad.ud03a01.persistencia.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="effectiveness")
@JsonInclude(Include.NON_NULL)
public class Effectiveness {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "offensiveType")
	private Types offensiveType;
	
	@ManyToOne
	@JoinColumn(name = "defensiveType")
	private Types defensiveType;
	
	@Column(name = "effect")
	private Float effect;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Types getOffensiveType() {
		return offensiveType;
	}

	public void setOffensiveType(Types offensiveType) {
		this.offensiveType = offensiveType;
	}

	public Types getDefensiveType() {
		return defensiveType;
	}

	public void setDefensiveType(Types defensiveType) {
		this.defensiveType = defensiveType;
	}

	public Float getEffect() {
		return effect;
	}

	public void setEffect(Float effect) {
		this.effect = effect;
	}
	
	
	
	
}
