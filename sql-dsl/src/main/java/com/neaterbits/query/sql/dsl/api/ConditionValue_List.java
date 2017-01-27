package com.neaterbits.query.sql.dsl.api;

import java.util.List;

public class ConditionValue_List extends ConditionValue_Collection {
	private final List<?> values;
	
	ConditionValue_List(List<?> values) {
		this.values = values;
	}

	List<?> getValues() {
		return values;
	}

	@Override
	<T, R> R visit(ConditionValueVisitor<T, R> visitor, T param) {
		return visitor.onList(this, param);
	}

	@Override
	EConditionValue getType() {
		return EConditionValue.LIST_OF_LITERAL;
	}
}
