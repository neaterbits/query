package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.Member;

abstract class AssociationAttribute extends EntityAttribute {

	AssociationAttribute(String name, String [] columns, AttributeType type, Class<?> javaType, Member member, Class<?> memberType) {
		super(name, columns, type, javaType, member, memberType);
	}

	@Override
	public void set(Object instance, Object value) {
		throw new UnsupportedOperationException("Not applicable to association attributes");
	}
}
