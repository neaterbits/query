package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public class CompanyResultVO {

	private String name;
	private BigDecimal stockPrice;
	private Integer yearFounded;
	
	public CompanyResultVO() {
		
	}

	public CompanyResultVO(String name) {
		this.name = name;
	}

	public CompanyResultVO(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}

	public CompanyResultVO(String name, Integer yearFounded) {
		this.name = name;
		this.yearFounded = yearFounded;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Integer getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(Integer yearFounded) {
		this.yearFounded = yearFounded;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		//result = prime * result + ((stockPrice == null) ? 0 : stockPrice.hashCode());
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
		CompanyResultVO other = (CompanyResultVO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (stockPrice == null) {
			if (other.stockPrice != null)
				return false;
		} else if (!stockPrice.equals(other.stockPrice))
			return false;
		if (yearFounded == null) {
			if (other.yearFounded != null)
				return false;
		} else if (!yearFounded.equals(other.yearFounded))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CompanyResultVO [name=" + name + ", stockPrice=" + stockPrice + "]";
	}
}
