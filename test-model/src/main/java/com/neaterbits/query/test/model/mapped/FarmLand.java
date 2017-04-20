package com.neaterbits.query.test.model.mapped;

import java.math.BigDecimal;
import java.util.Date;

public class FarmLand {

	private String farmName;
	private Date timeFarmFounded;
	private BigDecimal hectares;

	public FarmLand() {
		
	}

	public FarmLand(String farmName, BigDecimal hectares) {
		this.farmName = farmName;
		this.hectares = hectares;
	}

	public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}

	public BigDecimal getHectares() {
		return hectares;
	}

	public void setHectares(BigDecimal hectares) {
		this.hectares = hectares;
	}
	

	public Date getTimeFarmFounded() {
		return timeFarmFounded;
	}

	public void setTimeFarmFounded(Date timeFarmFounded) {
		this.timeFarmFounded = timeFarmFounded;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((farmName == null) ? 0 : farmName.hashCode());
		// result = prime * result + ((hectares == null) ? 0 : hectares.hashCode());
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
		FarmLand other = (FarmLand) obj;
		if (farmName == null) {
			if (other.farmName != null)
				return false;
		} else if (!farmName.equals(other.farmName))
			return false;
		if (hectares == null) {
			if (other.hectares != null)
				return false;
		//} else if (!hectares.equals(other.hectares))
		} else if (hectares.compareTo(other.hectares) != 0)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FarmLand [farmName=" + farmName + ", hectares=" + hectares + "]";
	}
}
