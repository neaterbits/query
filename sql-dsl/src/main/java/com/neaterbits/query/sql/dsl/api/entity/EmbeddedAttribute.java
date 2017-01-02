package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.Member;

final class EmbeddedAttribute extends EntityAttribute {

	EmbeddedAttribute(String name, String [] columns, AttributeType type, Class<?> javaType, Member member) {
		super(name, columns, type, javaType, member, null);
	}

	@Override
	public void set(Object instance, Object value) {
		throw new UnsupportedOperationException("TODO");
	}
}
