package com.neaterbits.query.sql.dsl.api.entity;

import java.util.List;

public interface IEntity extends IEntityFields {

	boolean isBaseType();
	
	ESubClassing getSubClassing();

	// When single table subclassing, this keeps the column name so that we can figure the type for native-queries
	String getSingleTableSubclassingColumn();
	
	List<IEntityFields> getSubEntitiesOrdered();
}
