package com.neaterbits.query.test.model.land;

import java.math.BigDecimal;

import javax.persistence.OneToOne;

import com.neaterbits.query.test.model.ownership.Owner;

/**
 * Base class for a farm land plot
 *
 */

public class LandPlot {

	private BigDecimal hectares;
	
	// land owner can be multiple, but that is handled by Owner hierarchy
	private Owner owner; 
	
	
	public void setHectares(BigDecimal hectares) {
		this.hectares = hectares;
	}

	public BigDecimal getHectares() {
		return hectares;
	}

	@OneToOne
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
}
