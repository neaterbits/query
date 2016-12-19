package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.Member;

final class OneToManyAttribute extends AssociationAttribute implements ICollectionEntityAttribute {

	OneToManyAttribute(String name, String [] columns, Class<?> javaType, Member member, Class<?> memberType) {
		super(name, columns, AttributeType.RELATION, javaType, member, memberType);
	}
}
