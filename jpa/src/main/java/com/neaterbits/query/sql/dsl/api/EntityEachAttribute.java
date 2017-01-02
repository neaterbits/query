package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.IEntityAttribute;

@FunctionalInterface
public interface EntityEachAttribute {
	
	void each(IEntityAttribute attr, String column, int idx);
	
}
