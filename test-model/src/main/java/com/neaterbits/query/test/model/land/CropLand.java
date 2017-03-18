package com.neaterbits.query.test.model.land;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity(name = "crop_land")
public class CropLand extends LandPlot {

	// The year made into cropland
	private Integer yearCultivated;

	public CropLand() {
		super();
	}

	public CropLand(BigDecimal hectares) {
		super(hectares);
	}

	public Integer getYearCultivated() {
		return yearCultivated;
	}

	public void setYearCultivated(Integer yearCultivated) {
		this.yearCultivated = yearCultivated;
	}
}
