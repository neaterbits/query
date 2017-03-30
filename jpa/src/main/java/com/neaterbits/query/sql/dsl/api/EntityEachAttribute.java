package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.IEntity;
import com.neaterbits.query.sql.dsl.api.entity.IEntityAttribute;
import com.neaterbits.query.sql.dsl.api.entity.IEntityFields;

@FunctionalInterface
public interface EntityEachAttribute {
	
	void each(IEntityFields entity, IEntityAttribute attr, String column, int idx);
	
}
