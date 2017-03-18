package com.neaterbits.query.test.model.land;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.neaterbits.query.test.model.ownership.Owner;

/**
 * Base class for a farm land plot
 *
 */

@Entity(name="land_plot")
public abstract class LandPlot {

	private Long id;
	
	private BigDecimal hectares;
	
	// land owner can be multiple, but that is handled by Owner hierarchy
	private Owner owner; 

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

	@Column
	public BigDecimal getHectares() {
		return hectares;
	}

	public void setHectares(BigDecimal hectares) {
		this.hectares = hectares;
	}


	@OneToOne
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
}
