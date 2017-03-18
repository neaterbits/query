package com.neaterbits.query.test.model.land;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Forest extends LandPlot {

	private SiteQuality siteQuality;
	
	public Forest() {
		super();
	}

	public Forest(BigDecimal hectares) {
		super(hectares);
	}

	public SiteQuality getSiteQuality() {
		return siteQuality;
	}

	public void setSiteQuality(SiteQuality bonitet) {
		this.siteQuality = bonitet;
	}
}
