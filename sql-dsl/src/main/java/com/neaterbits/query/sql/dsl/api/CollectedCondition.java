package com.neaterbits.query.sql.dsl.api;


abstract class CollectedCondition extends CollectedItem {

	abstract <T, R> R visit(ConditionVisitor<T, R> visitor, T param);

}
