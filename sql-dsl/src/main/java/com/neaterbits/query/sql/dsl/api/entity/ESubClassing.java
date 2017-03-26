package com.neaterbits.query.sql.dsl.api.entity;

public enum ESubClassing {
	
	NONE,				// entity is not subclassed
	MAPPED_SUPERCLASS, 	// all fields are copy-pasted onto concrete tables, difficult to do cross-type joins
	SINGLE_TABLE, 		// single table per class hierarchy
	TABLE_PER_CLASS, 	// one table per concrete class, keeping all columns from superclasses 
	JOINED, 			// one specific class to one table, using joins to gather information from all fields
	;

}
