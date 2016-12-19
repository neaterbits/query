package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.Member;

class OneToOneAttribute extends AssociationAttribute {

	OneToOneAttribute(String name, String [] columns, Class<?> javaType, Member member) {
		super(name, columns, AttributeType.RELATION, javaType, member, null);
	}
}
