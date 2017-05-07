package com.neaterbits.query.sql.dsl.api;

abstract class CollectedCondition_Basic extends CollectedCondition_NonNested {

	CollectedCondition_Basic(Expression lhs) {
		super(lhs);
	}
}
