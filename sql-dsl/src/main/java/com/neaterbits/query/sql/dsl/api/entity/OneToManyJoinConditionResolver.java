package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.Method;


public interface OneToManyJoinConditionResolver {
	
	Relation resolveOneToMany(Class<?> type1, Class<?> type2, Method collectionGetter);
	
}
