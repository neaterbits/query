package com.neaterbits.query.sql.dsl.api;


abstract class ConditionImpl extends QueryBuilderItem {

	private final Getter getter;

	abstract EClauseOperator getOperator();

	ConditionImpl(Getter getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		this.getter = getter;
	}


	final Getter getGetter() {
		return getter;
	}

	abstract <T, R> R visit(ConditionVisitor<T, R> visitor, T param);

}
