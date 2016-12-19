
package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.Member;

final class ScalarAttribute extends EntityAttribute {

	ScalarAttribute(String name, String [] columns, AttributeType type, Class<?> javaType, Member member) {
		super(name, columns, type, javaType, member, null);

		if (columns.length != 1) {
			throw new IllegalArgumentException("only one column");
		}
	}
}
