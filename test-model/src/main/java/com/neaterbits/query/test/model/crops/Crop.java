package com.neaterbits.query.test.model.crops;

import java.math.BigDecimal;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Inheritance(strategy=InheritanceType.JOINED)
public class Crop {

	private int year;
	private int harvest; // 1, 2, 
	
	private BigDecimal kg;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getHarvest() {
		return harvest;
	}

	public void setHarvest(int harvest) {
		this.harvest = harvest;
	}

	public BigDecimal getKg() {
		return kg;
	}

	public void setKg(BigDecimal kg) {
		this.kg = kg;
	}
	
}
