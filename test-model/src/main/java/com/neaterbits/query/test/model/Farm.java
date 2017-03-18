package com.neaterbits.query.test.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.neaterbits.query.test.model.land.LandPlot;
import com.neaterbits.query.test.model.ownership.Owner;

public class Farm {

	private Long id;
	private String name;
	private Owner owner;
	private Collection<LandPlot> landPlots;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

 
	// TODO: landplot connected to multiple? probably too complex, this is just a demo model
	// TODO: leasing ? can be seen as land.owner != farm.owner ? 
	// TODO: but perhaps this could be a short-hand join
	@OneToMany 	
	public Collection<LandPlot> getLandPlots() {
		return landPlots;
	}

	public void setLandPlots(Collection<LandPlot> landPlots) {
		this.landPlots = landPlots;
	}
}
