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
}
