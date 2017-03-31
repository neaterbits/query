package com.neaterbits.query.test.model.land;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.ownership.Owner;

/**
 * Base class for a farm land plot
 *
 */

@Entity
@Table(name="land_plot")
public abstract class LandPlot {

	private Long id;
	
	private BigDecimal hectares;
	
	// land owner can be multiple, but that is handled by Owner hierarchy
	private Owner owner; 
	
	private Farm farm;
	

	public LandPlot() {

	}
	
	public LandPlot(BigDecimal hectares) {
		if (hectares == null) {
			throw new IllegalArgumentException("hectares == null");
		}
		
		this.hectares = hectares;
	}

	public LandPlot(Long id, BigDecimal hectares) {
		if (id == null) {
			throw new IllegalArgumentException("id == null");
		}

		if (hectares == null) {
			throw new IllegalArgumentException("hectares == null");
		}

		this.id = id;
		this.hectares = hectares;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(precision=12, scale=5)
	public BigDecimal getHectares() {
		return hectares;
	}

	public void setHectares(BigDecimal hectares) {
		this.hectares = hectares;
	}

	@ManyToOne(targetEntity=Farm.class, fetch=FetchType.EAGER)
	//@JoinColumn(name="farm_fk")
	//@Column(name="farm_fk")
	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}

	@OneToOne
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id + ", hectares=" + hectares + ", owner=" + owner + 
				
					", farm=" + (farm != null ? farm.getFarmId() : "null") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// not in hashcode due to rounding -- result = prime * result + ((hectares == null) ? 0 : hectares.hashCode());
		
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LandPlot other = (LandPlot) obj;
		if (hectares == null) {
			if (other.hectares != null)
				return false;
		//} else if (!hectares.equals(other.hectares))
		} else if (hectares.compareTo(other.hectares) != 0)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
