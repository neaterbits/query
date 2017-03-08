package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

class CompanyAggregatesVO {
	private String name;
	private BigDecimal avgStockPrice;
	private BigDecimal sumStockPrice;
	
	private Integer foo;

	CompanyAggregatesVO() {
		
	}
	
	CompanyAggregatesVO(String name, BigDecimal avgStockPrice, BigDecimal sumStockPrice) {
		this.name = name;
		this.avgStockPrice = avgStockPrice;
		this.sumStockPrice = sumStockPrice;
	}

	public Integer getFoo() {
		return foo;
	}

	public void setFoo(Integer foo) {
		this.foo = foo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAvgStockPrice() {
		return avgStockPrice;
	}

	public void setAvgStockPrice(BigDecimal avgStockPrice) {
		this.avgStockPrice = avgStockPrice;
	}

	public BigDecimal getSumStockPrice() {
		return sumStockPrice;
	}

	public void setSumStockPrice(BigDecimal sumStockPrice) {
		this.sumStockPrice = sumStockPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avgStockPrice == null) ? 0 : avgStockPrice.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sumStockPrice == null) ? 0 : sumStockPrice.hashCode());
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
		CompanyAggregatesVO other = (CompanyAggregatesVO) obj;
		if (avgStockPrice == null) {
			if (other.avgStockPrice != null)
				return false;
		} else if (avgStockPrice.compareTo(other.avgStockPrice) != 0)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sumStockPrice == null) {
			if (other.sumStockPrice != null)
				return false;
		} else if (sumStockPrice.compareTo(other.sumStockPrice) != 0)
			return false;
		return true;
	}

}
