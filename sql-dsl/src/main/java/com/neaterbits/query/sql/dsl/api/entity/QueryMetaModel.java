package com.neaterbits.query.sql.dsl.api.entity;

import java.util.Set;

public interface QueryMetaModel extends OneToManyJoinConditionResolver {

	/**
	 * All classes that we might use lambdas to find attributes from
	 * Used for short-version of queries where we have to loop through all types
	 * to figure typing
	 * 
	 * @return the set of all manager-types
	 */
	
	Set<Class<?>> getAllManagedTypes();
}
