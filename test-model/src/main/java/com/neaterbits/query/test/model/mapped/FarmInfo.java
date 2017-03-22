package com.neaterbits.query.test.model.mapped;

public class FarmInfo {

	private String name;
	private String farmId;
	private String subFarmId;

	public FarmInfo() {
		
	}

	public FarmInfo(String name) {
		
		if (name == null) {
			throw new IllegalArgumentException("name == null");
		}
		
		this.name = name;
	}

	public FarmInfo(String name, String farmId) {
		
		if (name == null) {
			throw new IllegalArgumentException("name == null");
		}
		
		if (farmId == null) {
			throw new IllegalArgumentException("farmId == null");
		}
		
		this.name = name;
		this.farmId = farmId;
	}

	public FarmInfo(String name, String farmId, String subFarmId) {
		
		if (name == null) {
			throw new IllegalArgumentException("name == null");
		}
		
		if (farmId == null) {
			throw new IllegalArgumentException("farmId == null");
		}
		
		this.name = name;
		this.farmId = farmId;
		this.subFarmId = subFarmId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFarmId() {
		return farmId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}
	
	public String getSubFarmId() {
		return subFarmId;
	}

	public void setSubFarmId(String subFarmId) {
		this.subFarmId = subFarmId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((farmId == null) ? 0 : farmId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subFarmId == null) ? 0 : subFarmId.hashCode());
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
		FarmInfo other = (FarmInfo) obj;
		if (farmId == null) {
			if (other.farmId != null)
				return false;
		} else if (!farmId.equals(other.farmId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subFarmId == null) {
			if (other.subFarmId != null)
				return false;
		} else if (!subFarmId.equals(other.subFarmId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FarmInfo [name=" + name + ", farmId=" + farmId + ", subFarmId=" + subFarmId + "]";
	}
}
