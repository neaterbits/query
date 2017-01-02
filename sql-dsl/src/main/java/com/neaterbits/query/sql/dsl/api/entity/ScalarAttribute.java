
package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.Member;
import java.util.function.BiConsumer;


final class ScalarAttribute extends EntityAttribute {

	private final BiConsumer<Object, Object> setter;
	
	ScalarAttribute(String name, String [] columns, AttributeType type, Class<?> javaType, Member member, BiConsumer<Object, Object> setter) {
		super(name, columns, type, javaType, member, null);

		if (columns.length != 1) {
			throw new IllegalArgumentException("only one column");
		}
		
		if (setter == null) {
			throw new IllegalArgumentException("setter == null");
		}
		
		this.setter = setter;
	}

	@Override
	public void set(Object instance, Object value) {
		setter.accept(instance, value);
	}

}
