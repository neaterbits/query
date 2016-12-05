package com.neaterbits.query.sql.dsl.api;

abstract class ConditionValueImpl extends QueryBuilderItem {

	abstract <T, R> R visit(ConditionValueVisitor<T, R> visitor, T param);
	
}
