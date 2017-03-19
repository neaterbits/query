package com.neaterbits.query.test.model.land;

import java.math.BigDecimal;

import javax.persistence.Entity;

/**
 * utmark 
 */

@Entity
public class Uncultivated extends LandPlot {

	public Uncultivated() {
		super();
	}

	public Uncultivated(BigDecimal hectares) {
		super(hectares);
	}
}
