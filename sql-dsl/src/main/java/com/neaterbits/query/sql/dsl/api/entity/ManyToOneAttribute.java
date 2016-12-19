package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.Member;

final class ManyToOneAttribute extends AssociationAttribute {

	ManyToOneAttribute(String name, String [] columns, Class<?> javaType, Member member) {
		super(name, columns, AttributeType.RELATION, javaType, member, null);
	}
}
