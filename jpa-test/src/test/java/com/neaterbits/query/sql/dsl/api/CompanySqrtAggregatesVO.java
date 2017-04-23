package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public class CompanySqrtAggregatesVO {
	
	private Double sqrtAvgStockPrice;
	private Double sqrtSumStockPrice;

	private BigDecimal foo;
	
	
	CompanySqrtAggregatesVO() {
		
	}

	
	public CompanySqrtAggregatesVO(Double sqrtAvgStockPrice, Double sqrtSumStockPrice) {
		this.sqrtAvgStockPrice = sqrtAvgStockPrice;
		this.sqrtSumStockPrice = sqrtSumStockPrice;
	}
	

	public BigDecimal getFoo() {
		return foo;
	}


	public void setFoo(BigDecimal foo) {
		this.foo = foo;
	}


	public Double getSqrtAvgStockPrice() {
		return sqrtAvgStockPrice;
	}
	
	public void setSqrtAvgStockPrice(Double sqrtAvgStockPrice) {
		this.sqrtAvgStockPrice = sqrtAvgStockPrice;
	}
	
	public Double getSqrtSumStockPrice() {
		return sqrtSumStockPrice;
	}
	
	public void setSqrtSumStockPrice(Double sqrtSumStockPrice) {
		this.sqrtSumStockPrice = sqrtSumStockPrice;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sqrtAvgStockPrice == null) ? 0 : sqrtAvgStockPrice.hashCode());
		result = prime * result + ((sqrtSumStockPrice == null) ? 0 : sqrtSumStockPrice.hashCode());
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
		CompanySqrtAggregatesVO other = (CompanySqrtAggregatesVO) obj;
		if (sqrtAvgStockPrice == null) {
			if (other.sqrtAvgStockPrice != null)
				return false;
		} else if (!sqrtAvgStockPrice.equals(other.sqrtAvgStockPrice))
			return false;
		if (sqrtSumStockPrice == null) {
			if (other.sqrtSumStockPrice != null)
				return false;
		} else if (!sqrtSumStockPrice.equals(other.sqrtSumStockPrice))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "CompanySqrtAggregatesVO [sqrtAvgStockPrice=" + sqrtAvgStockPrice + ", sqrtSumStockPrice="
				+ sqrtSumStockPrice + "]";
	}

	
}
