package com.neaterbits.query.test.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.neaterbits.query.test.model.land.LandPlot;
import com.neaterbits.query.test.model.ownership.Owner;

@Entity
public class Farm {

	private Long id;
	private String name;
	private String farmId; //gård
	private String subFarmId; // bruk
	private Owner owner;
	private Collection<LandPlot> landPlots;

	public Farm() {
		
	}
	
	public Farm(String name) {
		this.name = name;

	}
	public Farm(String name, String farmId, String subFarmId) {
		this.name = name;
		this.farmId = farmId;
		this.subFarmId = subFarmId;
	}

	public Farm(Long id, String name) {
		
		if (id == null) {
			throw new IllegalArgumentException("id == null");
		}
		
		if (name == null) {
			throw new IllegalArgumentException("name == null");
		}
		
		this.id = id;
		this.name = name;
	}
	
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

	@Column(name="farm_id")
	public String getFarmId() {
		return farmId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}

	@Column(name="sub_farm_id")
	public String getSubFarmId() {
		return subFarmId;
	}

	public void setSubFarmId(String subFarmId) {
		this.subFarmId = subFarmId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((farmId == null) ? 0 : farmId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Farm other = (Farm) obj;
		if (farmId == null) {
			if (other.farmId != null)
				return false;
		} else if (!farmId.equals(other.farmId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return "Farm [id=" + id + ", name=" + name + ", farmId=" + farmId + ", subFarmId=" + subFarmId + ", owner="
				+ owner + ", landPlots=" + landPlots + "]";
	}
}
