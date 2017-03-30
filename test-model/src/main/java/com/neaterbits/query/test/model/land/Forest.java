package com.neaterbits.query.test.model.land;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Forest extends LandPlot {

	private SiteQuality siteQuality;
	
	public Forest() {
		super();
	}

	public Forest(BigDecimal hectares) {
		super(hectares);
	}
	
	public Forest(Long id, BigDecimal hectares) {
		super(id, hectares);
	}

	@OneToOne
	public SiteQuality getSiteQuality() {
		return siteQuality;
	}

	public void setSiteQuality(SiteQuality bonitet) {
		this.siteQuality = bonitet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((siteQuality == null) ? 0 : siteQuality.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Forest other = (Forest) obj;
		if (siteQuality == null) {
			if (other.siteQuality != null)
				return false;
		} else if (!siteQuality.equals(other.siteQuality))
			return false;
		return true;
	}
}
